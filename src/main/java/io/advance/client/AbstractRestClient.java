package io.advance.client;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public abstract class AbstractRestClient {
    private static final Logger log = Logger.getLogger(AbstractRestClient.class.getName());
    private static final long HALF_MINUTE_IN_MS = 30000L;
    private ThreadLocal<Client> clientThreadLocal = new ThreadLocal();
    private ClientBuilder clientBuilder = ClientBuilderHelper.createClientBuilder();

    public AbstractRestClient() {
    }

    protected Client getClient() {
        if (this.clientThreadLocal.get() == null) {
            this.setRestEasySpecificTimeouts();
            Client client = this.clientBuilder.build();
            client.property("javax.xml.ws.client.connectionTimeout", this.getConnectionTimeout());
            client.property("javax.xml.ws.client.receiveTimeout", this.getReceiveTimeout());
            this.clientThreadLocal.set(client);
        }

        return (Client)this.clientThreadLocal.get();
    }

    private void setRestEasySpecificTimeouts() {
        if ("org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder".equals(this.clientBuilder.getClass().getName())) {
            log.info("Setting timeouts for ResteasyClient");

            try {
                ClassLoader classLoader = this.clientBuilder.getClass().getClassLoader();
                Class<? extends ClientBuilder> resteasyClientBuilderClass = this.clientBuilder.getClass();
                Field establishConnectionTimeoutField = this.findField(resteasyClientBuilderClass, "establishConnectionTimeout");
                establishConnectionTimeoutField.set(this.clientBuilder, this.getConnectionTimeout());
                Field socketTimeoutField = this.findField(resteasyClientBuilderClass, "socketTimeout");
                socketTimeoutField.set(this.clientBuilder, this.getReceiveTimeout());
            } catch (IllegalAccessException | NoSuchFieldException var5) {
                log.log(Level.WARNING, "Attempted to configure the ResteasyClient's timeouts, but failed", var5);
            }
        }

    }

    private Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException var4) {
            if (clazz != Object.class) {
                return this.findField(clazz.getSuperclass(), fieldName);
            } else {
                throw var4;
            }
        }
    }

    protected long getConnectionTimeout() {
        return 30000L;
    }

    protected long getReceiveTimeout() {
        return 30000L;
    }
}
