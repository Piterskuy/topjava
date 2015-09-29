package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int MEAL_ID1 = START_SEQ + 2;
    public static final int MEAL_ID2 = START_SEQ + 3;
    public static final int MEAL_WRONG_ID = START_SEQ + 30000;

    public static final UserMeal MEAL1 = new UserMeal(MEAL_ID1, LocalDateTime.now(), "завтрак", 502);
    public static final UserMeal MEAL2 = new UserMeal(MEAL_ID2, LocalDateTime.now(), "обед", 1502);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
