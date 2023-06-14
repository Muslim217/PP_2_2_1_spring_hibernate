package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User returnUser(String modelQuery, int seriesQuery) {
        TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :car_name and series = :car_series")
                .setParameter("car_name", modelQuery)
                .setParameter("car_series", seriesQuery);
        Car carResult = findCarQuery.getSingleResult();
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        List<User> userList = query.getResultList();
        for (User user : userList) {
            if (user.getCar().equals(carResult)) {
                return user;
            }

        }

        return null;

    }
}








