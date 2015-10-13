package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    Collection<User> getAll();
    User getByEmail(String email) throws NotFoundException;

    void update(User user) throws NotFoundException;
    
    void evictCache();
//    List<User> getAll();

//    public void update(User user);
}
