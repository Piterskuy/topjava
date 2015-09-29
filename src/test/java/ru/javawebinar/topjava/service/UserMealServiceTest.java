package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


/**
 * Created by Piterskuy
 * on 29.09.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }
    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(MEAL_ID1, USER_ID);
        MATCHER.assertEquals(MEAL1, userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWrongUser() throws Exception {
        service.get(100003, USER_wrong_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_WRONG_ID, USER_ID);
    }
    
    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID2, USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        service.getBetweenDates(LocalDate.MIN, LocalDate.MAX, USER_ID);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        service.getBetweenDateTimes(LocalDateTime.MIN, LocalDateTime.MAX, USER_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL1, MEAL2), all);
    }


    @Test
    public void testSave() throws Exception {
        UserMeal testMeal = new UserMeal(LocalDateTime.now(), "ужин", 666);
        UserMeal created = service.save(testMeal, USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL1, MEAL2, created), service.getAll(USER_ID));
    }


    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = MEAL2;
        updated.setCalories(1233);
        updated.setDescription("полдник");
        service.update(updated, USER_ID);
        MealTestData.MATCHER.assertEquals(updated, service.get(MEAL_ID2, USER_ID));

    }
}