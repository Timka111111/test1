package hibernate.entity_manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    // аналог SessionFactory но в SessionFactory больше фишек
    private static final EntityManagerFactory emFactory;

    static {

        emFactory = Persistence.createEntityManagerFactory("com.timka.hibernate.entity_manager");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        if (emFactory != null) {
            emFactory.close();
        }
    }
}
