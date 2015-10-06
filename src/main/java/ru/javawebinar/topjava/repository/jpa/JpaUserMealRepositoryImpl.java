package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import ru.javawebinar.topjava.util.exception.NotFoundException;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(em.getReference(User.class, userId));
        if (userMeal.isNew()) {
            em.persist(userMeal);
            return userMeal;}
        else if(get(userMeal.getId(), userId)!=null){
            return em.merge(userMeal);
        }else{
            throw new NotFoundException("NFE");
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User ref = em.getReference(User.class, userId);

        Query query = em.createQuery("DELETE FROM UserMeal  WHERE id=:id and user=:user");
        return query.setParameter("id", id).setParameter("user", ref).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {User ref = em.getReference(User.class, userId);
        try{
            return em.createNamedQuery(UserMeal.GET, UserMeal.class).setParameter("id", id).setParameter("user", ref).getSingleResult();
        }
       catch(NoResultException e){
           throw new NotFoundException("NFE");
       }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        User ref = em.getReference(User.class, userId);
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("user", ref).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        User ref = em.getReference(User.class, userId);
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter("user", ref)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}