package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.LoggedUser;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected Integer id;
    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;
    protected LoggedUser user;

    public UserMeal(LocalDateTime dateTime, String description, int calories, LoggedUser user) {
        this(null, dateTime, description, calories,user);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories, LoggedUser user) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public LoggedUser getUserId() {
        return user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
