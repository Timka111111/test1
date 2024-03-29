package hibernate.entity_manager;



import com.timka.hibernate.PrepareDataApp;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        // аналог Session но в Session больше фишек
        EntityManager entityManager = null;
        try {
            entityManager = FactoryManager.getEntityManager();
            System.out.println(entityManager.getClass().getName());
            entityManager.getTransaction().begin();
            SimpleEntity se1 = entityManager.find(SimpleEntity.class, 1L);

            System.out.println(se1);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            SimpleEntity se2 = entityManager.find(SimpleEntity.class, 1L);
            System.out.println(se2);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.remove(se2);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            FactoryManager.close();
        }
    }
}
