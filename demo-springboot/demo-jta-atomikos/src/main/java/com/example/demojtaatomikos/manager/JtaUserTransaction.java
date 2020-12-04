package com.example.demojtaatomikos.manager;

import com.example.demojtaatomikos.util.MongoUtils;
import com.mongodb.TransactionOptions;
import com.mongodb.client.ClientSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.ResourceHolderSupport;
import org.springframework.transaction.support.SmartTransactionObject;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;
import org.springframework.util.Assert;

import javax.transaction.*;
import java.io.Serializable;
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

    public JtaUserTransaction(MongoTransactionManager mongoTransactionManager, MongoUtils mongoUtils){
        this.mongoTransactionManager = mongoTransactionManager;
        this.mongoUtils = mongoUtils;
    }


    @Override
    public void begin() throws NotSupportedException, SystemException {
//        checkSetup(); todo
        transactionManager.begin();
        mongoUtils.setSessionSynchronizationForTransactionBegin();
    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        Assert.notNull(mongoTransactionManager.getDbFactory(),"DbFactory must not be null!");
        if (Objects.nonNull(TransactionSynchronizationManager.getResource(mongoTransactionManager.getDbFactory()))){
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

    public static String s(){
        return null;
    }



    protected static class MongoTransactionObject implements SmartTransactionObject{
        @Nullable
        private MongoResourceHolder resourceHolder;

        MongoTransactionObject(@Nullable MongoResourceHolder resourceHolder){
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


        void startTransaction(@Nullable TransactionOptions options){
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

    class MongoResourceHolder extends ResourceHolderSupport{
        private @Nullable ClientSession session;
        private MongoDatabaseFactory dbFactory;

        MongoResourceHolder(@Nullable ClientSession session, MongoDatabaseFactory dbFactory){
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
