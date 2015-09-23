package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        LoggedUser user1 = new LoggedUser();
        LoggedUser user2 = new LoggedUser();
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, user1));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, user1));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, user1));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, user2));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, user2));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, user2));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int id) {
        repository.remove(id);
        return true;
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return repository.values();
    }
}

