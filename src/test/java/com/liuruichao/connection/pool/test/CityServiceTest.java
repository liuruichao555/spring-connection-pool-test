package com.liuruichao.connection.pool.test;

import com.google.common.collect.Iterables;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

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
    public void testAddData2() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Random random = new Random();

        Callable<Boolean> task = () -> {
            for (int j = 0; j < 100000; j++) {
                List<City> cities = Lists.newArrayList();

                for (int i = 0; i < 10000; i++) {
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

                City last = Iterables.getLast(cities);
                System.out.println("batch insert size: " + cities.size()
                        + ", thread name: " + Thread.currentThread().getName() + ", last city id: " + last.getId());
            }

            return true;
        };

        List<Future<Boolean>> futures = Lists.newArrayList();
        futures.add(executor.submit(task));
        futures.add(executor.submit(task));
        futures.add(executor.submit(task));
        futures.add(executor.submit(task));

        for (Future<Boolean> future : futures) {
            future.get();
        }
    }

    @Test
    public void testQueryByNames() {
        cityService.queryByNames(Lists.newArrayList());
    }

    @Test
    public void testTransactional() {
        City city = new City();
        city.setName("hehe");
        city.setAliasName("hh");
        city.setProvincesId(5);
        city.setScore1(50);
        city.setScore2(60);

        cityService.addTestTransactional(Lists.newArrayList(city));
    }
}
