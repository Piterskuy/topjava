package ru.javawebinar.topjava.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id and m.user=:user"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=:id and m.user=:user"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user=:user ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT m FROM UserMeal m WHERE m.user=:user and m.dateTime>=:startDate and m.dateTime<:endDate ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal m SET m.id=:id,m.dateTime=:dateTime,m.description=:description,m.calories=:calories  WHERE m.id=:id"),

})
@Entity
@Table(name = "meals")
public class UserMeal extends BaseEntity {


    public static final String DELETE = "UserMeal.delete";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET_BETWEEN = "UserMeal.getBetween";
    public static final String GET = "UserMeal.get";
    public static final String UPDATE = "UserMeal.update";


    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @NotEmpty
    protected LocalDateTime dateTime;

    public User getUser() {
        return user;
    }

    @Column(name = "description")
    protected String description;

    @NotEmpty
    @Column(name = "calories")
    @Digits(fraction = 0, integer = 4)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}