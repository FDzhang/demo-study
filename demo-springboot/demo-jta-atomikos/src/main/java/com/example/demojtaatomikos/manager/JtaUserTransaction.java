package com.example.demojtaatomikos.manager;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.TransactionManagerImp;
import com.atomikos.util.SerializableObjectFactory;
import com.example.demojtaatomikos.util.MongoUtils;
import com.mongodb.MongoException;
import com.mongodb.TransactionOptions;
import com.mongodb.client.ClientSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.*;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.*;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class JtaUserTransaction implements UserTransaction, Serializable, Referenceable {
    private static final long serialVersionUID = -865418426269785202L;
    private transient TransactionManager transactionManager;
    private transient MongoTransactionManager mongoTransactionManager;
    private final transient MongoUtils mongoUtils;

    public JtaUserTransaction(MongoTransactionManager mongoTransactionManager, MongoUtils mongoUtils) {
        this.mongoTransactionManager = mongoTransactionManager;
        this.mongoUtils = mongoUtils;
    }

    /**
     * @see javax.transaction.UserTransaction
     */
    @Override
    public void begin() throws NotSupportedException, SystemException {
        checkSetup();
        transactionManager.begin();
        mongoUtils.setSessionSynchronizationForTransactionBegin();
    }

    /**
     * @see javax.transaction.UserTransaction
     */
    @Override
    public void commit() throws javax.transaction.RollbackException, javax.transaction.HeuristicMixedException,
            javax.transaction.HeuristicRollbackException, javax.transaction.SystemException {
        Assert.notNull(mongoTransactionManager.getDbFactory(), "DbFactory must not be null!");
        if (Objects.nonNull(TransactionSynchronizationManager.getResource(mongoTransactionManager.getDbFactory()))) {
            MongoTransactionObject mongoTransactionObject = extractMongoTransaction(getMongoTransaction());
            MongoResourceHolder resourceHolder = newResourceHolder(new DefaultTransactionDefinition());
            mongoTransactionObject.setResourceHolder(resourceHolder);
            try {
                mongoTransactionObject.commitTransaction();
                TransactionSynchronizationManager.unbindResource(mongoTransactionManager.getDbFactory());
                mongoTransactionObject.getRequiredResourceHolder().clear();
                mongoTransactionObject.closeSession();
                mongoUtils.setSessionSynchronizationForTransactionCompletion();
            } catch (Exception ex) {
                throw new TransactionSystemException(String.format("Could not commit Mongo transaction for session %s.",
                        debugString(mongoTransactionObject.getSession())), ex);
            }
        }
        checkSetup();
        transactionManager.commit();
    }

    /**
     * @see javax.transaction.UserTransaction
     */
    @Override
    public void rollback() throws SystemException {
        Assert.notNull(mongoTransactionManager.getDbFactory(), "Db factory must not be null");
        if (Objects.nonNull(TransactionSynchronizationManager.getResource(mongoTransactionManager.getDbFactory()))) {
            MongoTransactionObject mongoTransactionObject = extractMongoTransaction(getMongoTransaction());
            MongoResourceHolder resourceHolder = newResourceHolder(new DefaultTransactionDefinition());
            mongoTransactionObject.setResourceHolder(resourceHolder);
            try {
                mongoTransactionObject.abortTransaction();
                TransactionSynchronizationManager.unbindResource(mongoTransactionManager.getDbFactory());
                mongoTransactionObject.getRequiredResourceHolder().clear();
                mongoTransactionObject.closeSession();
                mongoUtils.setSessionSynchronizationForTransactionCompletion();
            } catch (MongoException ex) {
                throw new TransactionSystemException(String.format("Could not abort Mongo transaction for session %s.",
                        debugString(mongoTransactionObject.getSession())), ex);
            }
        }
        checkSetup();
        transactionManager.rollback();
    }

    /**
     * 不抛出异常进行回滚
     *
     * @see javax.transaction.UserTransaction
     */
    @Override
    public void setRollbackOnly() throws SystemException {
        checkSetup();
        transactionManager.setRollbackOnly();
    }

    /**
     * @see javax.transaction.UserTransaction
     */
    @Override
    public int getStatus() throws SystemException {
        checkSetup();
        return transactionManager.getStatus();
    }

    /**
     * @see javax.transaction.UserTransaction
     */
    @Override
    public void setTransactionTimeout(int seconds) throws SystemException {
        checkSetup();
        transactionManager.setTransactionTimeout(seconds);
    }

    /**
     * IMPLEMENTATION OF REFERENCEABLE
     */
    @Override
    public Reference getReference() throws NamingException {
        return SerializableObjectFactory.createReference(this);
    }

    protected int determineTimeout(TransactionDefinition definition) {
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            return definition.getTimeout();
        }
        return TransactionDefinition.TIMEOUT_DEFAULT;
    }

    /**
     * Referenceable mechanism requires later setup of transactionManager, otherwise binding
     * <p>
     * into JNDI already requires that TM is running.
     */
    private void checkSetup() {
        synchronized (TransactionManagerImp.class) {
            transactionManager = TransactionManagerImp.getTransactionManager();
            if (Objects.isNull(transactionManager)) {
                UserTransactionService uts = new UserTransactionServiceImp();
                uts.init();
                transactionManager = TransactionManagerImp.getTransactionManager();
            }
        }
    }

    private Object getMongoTransaction() {
        MongoDatabaseFactory mongoDbFactory = mongoTransactionManager.getDbFactory();
        Assert.notNull(mongoDbFactory, "Db factory must not be null");
        MongoResourceHolder resourceHolder = (MongoResourceHolder) TransactionSynchronizationManager
                .getResource(mongoDbFactory);
        return new MongoTransactionObject(resourceHolder);
    }

    private static MongoTransactionObject extractMongoTransaction(Object transaction) {
        Assert.isInstanceOf(MongoTransactionObject.class, transaction,
                () -> String.format("Expected to find a %s but it turned out to be %s.", MongoTransactionObject.class,
                        transaction.getClass()));
        return (MongoTransactionObject) transaction;
    }

    private MongoResourceHolder newResourceHolder(TransactionDefinition definition) {
        MongoDatabaseFactory dbFactory = mongoTransactionManager.getDbFactory();
        Class<MongoDatabaseUtils> mongoDatabaseUtilsClazz = MongoDatabaseUtils.class;
        ClientSession session = null;
        try {
            Method doGetSession = mongoDatabaseUtilsClazz.getDeclaredMethod(
                    "doGetSession", MongoDatabaseFactory.class, SessionSynchronization.class);
            doGetSession.setAccessible(true);
            session = (ClientSession) doGetSession.invoke(
                    mongoDatabaseUtilsClazz.newInstance(), dbFactory, SessionSynchronization.ALWAYS);
        } catch (Exception e) {
            log.error("getSession err;", e.getCause());
        }
        if (Objects.nonNull(session)) {
            MongoResourceHolder resourceHolder = new MongoResourceHolder(session, dbFactory);
            resourceHolder.setTimeoutIfNotDefaulted(determineTimeout(definition));
            return resourceHolder;
        }
        return null;
    }

    private static String debugString(@Nullable ClientSession session) {
        if (session == null) {
            return "null";
        }
        String debugString = String.format("[%s@%s ", ClassUtils.getShortName(session.getClass()),
                Integer.toHexString(session.hashCode()));
        try {
            if (session.getServerSession() != null) {
                debugString += String.format("id = %s, ", session.getServerSession().getIdentifier());
                debugString += String.format("causallyConsistent = %s, ", session.isCausallyConsistent());
                debugString += String.format("txActive = %s, ", session.hasActiveTransaction());
                debugString += String.format("txNumber = %d, ", session.getServerSession().getTransactionNumber());
                debugString += String.format("closed = %s, ", session.getServerSession().isClosed());
                debugString += String.format("clusterTime = %s", session.getClusterTime());
            } else {
                debugString += "id = n/a";
                debugString += String.format("causallyConsistent = %s, ", session.isCausallyConsistent());
                debugString += String.format("txActive = %s, ", session.hasActiveTransaction());
                debugString += String.format("clusterTime = %s", session.getClusterTime());
            }
        } catch (RuntimeException e) {
            debugString += String.format("error = %s", e.getMessage());
        }
        debugString += "]";
        return debugString;
    }

    /**
     * @see org.springframework.data.mongodb.MongoResourceHolder
     */
    protected static class MongoTransactionObject implements SmartTransactionObject {
        @Nullable
        private MongoResourceHolder resourceHolder;

        MongoTransactionObject(@Nullable MongoResourceHolder resourceHolder) {
            this.resourceHolder = resourceHolder;
        }

        /**
         * Set the {@link MongoResourceHolder}.
         *
         * @param resourceHolder can be {@literal null}.
         */
        void setResourceHolder(@Nullable MongoResourceHolder resourceHolder) {
            this.resourceHolder = resourceHolder;
        }

        /**
         * @return {@literal true} if a {@link MongoResourceHolder} is set.
         */
        final boolean hasResourceHolder() {
            return resourceHolder != null;
        }

        /**
         * Start a MongoDB transaction optionally given {@link TransactionOptions}.
         *
         * @param options can be {@literal null}
         */

        void startTransaction(@Nullable TransactionOptions options) {
            ClientSession session = getRequiredSession();
            if (options != null) {
                session.startTransaction(options);
            } else {
                session.startTransaction();
            }
        }

        /**
         * Commit the transaction.
         */
        public void commitTransaction() {
            getRequiredSession().commitTransaction();
        }

        /**
         * Rollback (abort) the transaction.
         */
        public void abortTransaction() {
            getRequiredSession().abortTransaction();
        }

        /**
         * Close a {@link ClientSession} without regard to its transactional state.
         */
        void closeSession() {
            ClientSession session = getRequiredSession();
            if (session.getServerSession() != null && !session.getServerSession().isClosed()) {
                session.close();
            }
        }

        @Nullable
        public ClientSession getSession() {
            return resourceHolder != null ? resourceHolder.getSession() : null;
        }

        private MongoResourceHolder getRequiredResourceHolder() {
            Assert.state(resourceHolder != null, "MongoResourceHolder is required but not present. o_O");
            return resourceHolder;
        }

        private ClientSession getRequiredSession() {
            ClientSession session = getSession();
            Assert.state(session != null, "A Session is required but it turned out to be null.");
            return session;
        }

        /**
         * (non-Javadoc)
         *
         * @see org.springframework.transaction.support.SmartTransactionObject#isRollbackOnly()
         */
        @Override
        public boolean isRollbackOnly() {
            return this.resourceHolder != null && this.resourceHolder.isRollbackOnly();
        }

        /**
         * (non-Javadoc)
         *
         * @see org.springframework.transaction.support.SmartTransactionObject#flush()
         */
        @Override
        public void flush() {
            TransactionSynchronizationUtils.triggerFlush();
        }

    }

    /**
     * @see org.springframework.data.mongodb.MongoResourceHolder
     */
    class MongoResourceHolder extends ResourceHolderSupport {
        private @Nullable
        ClientSession session;
        private MongoDatabaseFactory dbFactory;

        /**
         * Create a new {@link com.shero.sport.web.conf.JtaTransactionImp.MongoResourceHolder} for a given {@link ClientSession session}.
         *
         * @param session   the associated {@link ClientSession}. Can be {@literal null}.
         * @param dbFactory the associated {@link MongoDbFactory}. must not be {@literal null}.
         */
        MongoResourceHolder(@Nullable ClientSession session, MongoDatabaseFactory dbFactory) {
            this.session = session;
            this.dbFactory = dbFactory;
        }

        /**
         * @return the associated {@link ClientSession}. Can be {@literal null}.
         */
        @Nullable
        ClientSession getSession() {
            return session;
        }

        /**
         * @return the required associated {@link ClientSession}.
         * @throws IllegalStateException if no {@link ClientSession} is associated with this {@link com.shero.sport.web.conf.JtaTransactionImp.MongoResourceHolder}.
         * @since 2.1.3
         */
        ClientSession getRequiredSession() {
            ClientSession session = getSession();
            if (session == null) {
                throw new IllegalStateException("No session available!");
            }
            return session;
        }

        /**
         * @return the associated {@link MongoDbFactory}.
         */
        public MongoDatabaseFactory getDbFactory() {
            return dbFactory;
        }

        /**
         * Set the {@link ClientSession} to guard.
         *
         * @param session can be {@literal null}.
         */
        public void setSession(@Nullable ClientSession session) {
            this.session = session;
        }

        /**
         * Only set the timeout if it does not match the {@link TransactionDefinition#TIMEOUT_DEFAULT default timeout}.
         *
         * @param seconds
         */
        void setTimeoutIfNotDefaulted(int seconds) {
            if (seconds != TransactionDefinition.TIMEOUT_DEFAULT) {
                setTimeoutInSeconds(seconds);
            }
        }

        /**
         * @return {@literal true} if session is not {@literal null}.
         */
        boolean hasSession() {
            return session != null;
        }

        /**
         * @return {@literal true} if the session is active and has not been closed.
         */
        boolean hasActiveSession() {
            if (!hasSession()) {
                return false;
            }
            return hasServerSession() && !getRequiredSession().getServerSession().isClosed();
        }

        /**
         * @return {@literal true} if the session has an active transaction.
         * @see #hasActiveSession()
         * @since 2.1.3
         */
        boolean hasActiveTransaction() {
            if (!hasActiveSession()) {
                return false;
            }
            return getRequiredSession().hasActiveTransaction();
        }

        /**
         * @return {@literal true} if the {@link ClientSession} has a {@link com.mongodb.session.ServerSession} associated
         * <p>
         * that is accessible via {@link ClientSession#getServerSession()}.
         */
        boolean hasServerSession() {
            try {
                return getRequiredSession().getServerSession() != null;
            } catch (IllegalStateException serverSessionClosed) {
                // ignore
            }
            return false;
        }
    }
}