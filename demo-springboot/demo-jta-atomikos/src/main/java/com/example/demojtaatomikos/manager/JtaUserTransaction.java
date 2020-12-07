package com.example.demojtaatomikos.manager;

import com.example.demojtaatomikos.util.MongoUtils;
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

import javax.transaction.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/4 16:52
 */
@Slf4j
public class JtaUserTransaction implements UserTransaction, Serializable {
    private static final long serialVersionUID = 4340866748256003265L;

    private transient TransactionManager transactionManager;
    private transient MongoTransactionManager mongoTransactionManager;
    private final transient MongoUtils mongoUtils;

    public JtaUserTransaction(MongoTransactionManager mongoTransactionManager, MongoUtils mongoUtils) {
        this.mongoTransactionManager = mongoTransactionManager;
        this.mongoUtils = mongoUtils;
    }


    @Override
    public void begin() throws NotSupportedException, SystemException {
//        checkSetup(); todo
        transactionManager.begin();
        mongoUtils.setSessionSynchronizationForTransactionBegin();
    }

    private static MongoTransactionObject extractMongoTransaction(Object transaction) {
        Assert.isInstanceOf(MongoTransactionObject.class, transaction,
                () -> String.format("Expected to find a %s but it turned out to be %s.", MongoTransactionObject.class,
                        transaction.getClass()));
        return (MongoTransactionObject) transaction;
    }

    private Object getMongoTransaction() {
        MongoDatabaseFactory mongoDatabaseFactory = mongoTransactionManager.getDbFactory();
        Assert.notNull(mongoDatabaseFactory, "Db factory must not be null");
        MongoResourceHolder resourceHolder = (MongoResourceHolder) TransactionSynchronizationManager.getResource(mongoDatabaseFactory);
        return new MongoTransactionObject(resourceHolder);
    }

    private MongoResourceHolder newResourceHolder(TransactionDefinition definition) {
        MongoDatabaseFactory dbFactory = mongoTransactionManager.getDbFactory();
        Class<MongoDatabaseUtils> mongoDatabaseUtilsClass = MongoDatabaseUtils.class;
        ClientSession session = null;
        try {
            Method doGetSession = mongoDatabaseUtilsClass.getDeclaredMethod(
                    "doGetSession", MongoDatabaseFactory.class, SessionSynchronization.class);
            doGetSession.setAccessible(true);
            session = (ClientSession) doGetSession.invoke(
                    mongoDatabaseUtilsClass.newInstance(), dbFactory, SessionSynchronization.ALWAYS
            );
        } catch (Exception e) {
            log.error("getSession srr;", e.getCause());
        }
        if (Objects.nonNull(session)) {
            MongoResourceHolder resourceHolder = new MongoResourceHolder(session, dbFactory);
            resourceHolder.setTimeoutIfNotDefaulted(determineTimeout(definition));
            return resourceHolder;
        }
        return null;
    }

    private int determineTimeout(TransactionDefinition definition) {
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            return definition.getTimeout();
        }
        return TransactionDefinition.TIMEOUT_DEFAULT;
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
        } catch (Exception e) {
            debugString += String.format("error = %s", e.getMessage());
        }
        debugString += "]";
        return debugString;
    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
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
            }
        }
    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException, SystemException {

    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {

    }

    @Override
    public int getStatus() throws SystemException {
        return 0;
    }

    @Override
    public void setTransactionTimeout(int i) throws SystemException {

    }

    public static String s() {
        return null;
    }


    protected static class MongoTransactionObject implements SmartTransactionObject {
        @Nullable
        private MongoResourceHolder resourceHolder;

        MongoTransactionObject(@Nullable MongoResourceHolder resourceHolder) {
            this.resourceHolder = resourceHolder;
        }

        void setResourceHolder(@Nullable MongoResourceHolder resourceHolder) {
            this.resourceHolder = resourceHolder;
        }

        final boolean hasResourceHolder() {
            return resourceHolder != null;
        }


        @Nullable
        public ClientSession getSession() {
            return resourceHolder != null ? resourceHolder.getSession() : null;
        }

        private ClientSession getRequiredSession() {
            ClientSession session = getSession();
            Assert.state(session != null, "A Session is required but it turned out to be null.");
            return session;
        }


        void startTransaction(@Nullable TransactionOptions options) {
            ClientSession session = getRequiredSession();
        }

        public void commitTransaction() {
            getRequiredSession().commitTransaction();
        }

        public void abortTransaction() {
            getRequiredSession().abortTransaction();
        }

        void closeSession() {
            ClientSession session = getRequiredSession();
            if (session.getServerSession() != null && !session.getServerSession().isClosed()) {
                session.close();
            }
        }

        private MongoResourceHolder getRequiredResourceHolder() {
            Assert.state(resourceHolder != null, "MongoResourceHolder is required but not present. o_O");
            return resourceHolder;
        }

        @Override
        public boolean isRollbackOnly() {
            return this.resourceHolder != null && this.resourceHolder.isRollbackOnly();
        }

        @Override
        public void flush() {
            TransactionSynchronizationUtils.triggerFlush();
        }
    }

    class MongoResourceHolder extends ResourceHolderSupport {
        private @Nullable
        ClientSession session;
        private MongoDatabaseFactory dbFactory;

        MongoResourceHolder(@Nullable ClientSession session, MongoDatabaseFactory dbFactory) {
            this.session = session;
            this.dbFactory = dbFactory;
        }

        @Nullable
        ClientSession getSession() {
            return session;
        }

        ClientSession getRequiredSession() {
            ClientSession session = getSession();
            if (session == null) {
                throw new IllegalStateException("No session available!");
            }
            return session;
        }

        public MongoDatabaseFactory getDbFactory() {
            return dbFactory;
        }

        public void setSession(@Nullable ClientSession session) {
            this.session = session;
        }

        void setTimeoutIfNotDefaulted(int seconds) {
            if (seconds != TransactionDefinition.TIMEOUT_DEFAULT) {
                setTimeoutInSeconds(seconds);
            }
        }

        boolean hasSession() {
            return session != null;
        }

        boolean hasActiveSession() {
            if (!hasSession()) {
                return false;
            }
            return hasServerSession() && !getRequiredSession().getServerSession().isClosed();
        }

        boolean hasActiveTransaction() {
            if (!hasActiveSession()) {
                return false;
            }
            return getRequiredSession().hasActiveTransaction();
        }

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
