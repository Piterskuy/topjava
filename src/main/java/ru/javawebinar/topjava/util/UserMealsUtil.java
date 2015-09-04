package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }


    public static boolean isBetween(LocalTime check, LocalTime startTime, LocalTime endTime) {
        return ((check.equals(startTime) || check.isAfter(startTime)) &&
                (check.equals(endTime) || check.isBefore(endTime)));
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        //Подсчёт суммарных калорий за день
        HashMap<LocalDate,Integer> daysCal= new HashMap<>();

        //Готовая таблица на вывод
        List<UserMealWithExceed> mealExceeded = new ArrayList();

        LocalDate curDate;
        int curCalories;
        for(UserMeal meal : mealList) {
           if(isBetween(meal.getDateTime().toLocalTime(),startTime,endTime)){
               //Значения текущей записи
               curDate=meal.getDateTime().toLocalDate();
               curCalories=meal.getCalories();

               //Подсчитываем калории в сумме за день
               if(daysCal.get(curDate)!=null){
                   curCalories+=daysCal.get(curDate);
               }
               daysCal.put(curDate, curCalories);

           }
       }

        boolean curExceeded;
        for(UserMeal meal : mealList) {
            if(isBetween(meal.getDateTime().toLocalTime(),startTime,endTime)) {
                //Значения текущей записи
                curDate=meal.getDateTime().toLocalDate();
                curCalories=daysCal.get(curDate);
                curExceeded=curCalories>caloriesPerDay;

                mealExceeded.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), curCalories, curExceeded));

            }
        }

        //LogOut
        for(UserMealWithExceed meal : mealExceeded){
            System.out.println(meal.getDateTime());
            System.out.println(meal.getCalories());
            System.out.println(meal.getExceed());
        }

//        final double[] total = mealList
////        UserMeal mmm = mealList
//                .stream()
//                .filter(meal -> isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
//                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate()));
////                .mapToInt(UserMeal::getCalories)
////                .sum();
////                .forEach(System.out::println);
//
////        System.out.println(total);
//        for(UserMeal meal : mealList) {
//            System.out.println(meal.getDateTime());
////            System.out.println(meal.getCalories());
//        //    System.out.println(meal.getExceed());
//        }
        return mealExceeded;
    }
}
