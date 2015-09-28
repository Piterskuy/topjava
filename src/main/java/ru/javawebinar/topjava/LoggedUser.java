package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.BaseEntity;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {
    public static int id = BaseEntity.START_SEQ;

    public static int id() {
        return 1;
    }

    public static int getCaloriesPerDay() {
        return 2000;
    }
}