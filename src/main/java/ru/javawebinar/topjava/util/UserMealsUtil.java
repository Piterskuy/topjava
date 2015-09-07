package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.*;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil{
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> mealExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(12, 0), LocalTime.of(13,0), 2000);
        System.out.println(mealExceeded.toString());
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        //Подсчёт суммарных калорий за день
        HashMap<LocalDate,Integer> sumCaloriesPerDay = new HashMap<>();

        for(UserMeal meal : mealList) {
            LocalDate mealDate=meal.getDateTime().toLocalDate();
            int mealCalories=meal.getCalories();
            Integer curDaySumCalories=sumCaloriesPerDay.get(mealDate);

            if(curDaySumCalories==null)
                curDaySumCalories=mealCalories;
            else
                curDaySumCalories+=mealCalories;

            sumCaloriesPerDay.put(mealDate, curDaySumCalories);
       }

        //Таблица на вывод
        List<UserMealWithExceed> mealExceeded = new ArrayList();

        for(UserMeal meal : mealList) {
            if(TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime,endTime)) {
                mealExceeded.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), sumCaloriesPerDay.get(meal.getDateTime().toLocalDate())>caloriesPerDay));
            }
        }
        return mealExceeded;

    }
}
