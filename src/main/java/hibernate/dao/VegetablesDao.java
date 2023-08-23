package hibernate.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public class VegetablesDao extends AbstractDao<Vegetables, Long> {
    public VegetablesDao(SessionFactory sessionFactory) {
        super(sessionFactory, Vegetables.class, Long.class);
    }

    public List<Vegetables> findByUserId(Long userId) {
        // ...
        return null;
    }
    // VegetablesDao orderDao = new VegetablesDao(...);
    // AbstractDao<Vegetables, Long> orderDao = new AbstractDao<>(...);
}
