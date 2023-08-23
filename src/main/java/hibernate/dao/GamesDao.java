package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GamesDao {
    private SessionFactory sessionFactory;

    public GamesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Games findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Games games = session.get(Games.class, id);
            session.getTransaction().commit();
            return games;
        }
    }

    public List<Games> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Games> games = (List<Games>)session.createQuery("from Games", List.class);
            session.getTransaction().commit();
            return games;
        }
    }

    public Games saveOrUpdate(Games games) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(games);
            session.getTransaction().commit();
            return games;
        }
    }

    public void delete(Games games) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(games);
            session.getTransaction().commit();
        }
    }
}
