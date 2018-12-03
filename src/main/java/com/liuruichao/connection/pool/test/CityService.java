package com.liuruichao.connection.pool.test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * CityService
 *
 * @author liuruichao
 * Created on 2018/9/17 20:18
 */
@Service
@Slf4j
public class CityService {
    @Resource
    private CityMapper cityMapper;

    public List<City> list() {
        return cityMapper.list();
    }

    public void addTestData() {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        List<Future<Boolean>> futures = Lists.newArrayListWithCapacity(5);
        CityService targetService = ((CityService) AopContext.currentProxy());

        for (int j = 0; j < 5; j++) {
            Future<Boolean> future = executors.submit(() -> {
                Random random = new Random(System.currentTimeMillis());
                City city = null;
                List<City> list = Lists.newArrayListWithCapacity(10000);
                long totalRecords = 1000000000L;
                for (long i = 0; i < totalRecords; i++) {
                    city = new City();
                    city.setName("test_" + i);
                    city.setProvincesId(random.nextInt(30));
                    city.setAliasName("alias_" + i);
                    city.setScore1(random.nextInt(100));
                    city.setScore2(random.nextInt(100));
                    list.add(city);
                    if (list.size() == 10000) {
                        targetService.addTestData(list);
                        list.clear();
                    }
                }

                if (list.size() > 0) {
                    targetService.addTestData(list);
                }

                return true;
            });

            futures.add(future);
        }

        futures.forEach(booleanFuture -> {
            try {
                if (booleanFuture.get()) {
                    System.out.println("success");
                } else {
                    System.out.println("fail");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTestData(List<City> list) {
        cityMapper.insert(list);
    }
}
