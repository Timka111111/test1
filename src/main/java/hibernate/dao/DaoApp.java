package hibernate.dao;


import com.timka.hibernate.PrepareDataApp;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/dao/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            init();
            GamesDao gamesDao = new GamesDao(factory);
            Games g = gamesDao.findById(1L);
            System.out.println(g);

//            AbstractDao<Vegetables, Long> dao = new AbstractDao<>(factory, Vegetables.class, Long.class);
//            Vegetables v = dao.findById(1L);
//            System.out.println(v);
        } finally {
            close();
        }
    }

    public static void close() {
        factory.close();
    }
}
