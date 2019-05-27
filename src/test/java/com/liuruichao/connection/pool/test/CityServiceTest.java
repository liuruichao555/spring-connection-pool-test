package com.liuruichao.connection.pool.test;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * CityServiceTest
 *
 * @author liuruichao
 * Created on 2018/11/20 14:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class CityServiceTest {
    @Resource
    private CityService cityService;

    @Test
    public void testAddData() {
        cityService.addTestData();
    }

    @Test
    public void testAddData2() {
        List<City> cities = Lists.newArrayList();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.setName("test_" + i);
            city.setProvincesId(random.nextInt(30));
            city.setAliasName("alias_" + i);
            city.setScore1(random.nextInt(100));
            city.setScore2(random.nextInt(100));
            city.setIsDelete(0);
            cities.add(city);
        }
        cityService.addTestData(cities);
    }

    @Test
    public void testQueryByNames() {
        cityService.queryByNames(Lists.newArrayList());
    }
}
