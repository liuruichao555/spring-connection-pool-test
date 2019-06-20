package com.liuruichao.connection.pool.test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    @Resource
    private PlatformTransactionManager transactionManager;

    public List<City> list() {
        return cityMapper.list();
    }

    public List<City> queryByNames(Collection<String> names) {
        return cityMapper.selectByNames(names);
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

    public void addTestTransactional(List<City> list) {
        // 当前有事务就获取当前事务，如果没有，新开一个事务
        // 加上@Transactional注解就是有事务(获取当前事务)，不加@Transactional注解就没有事务(会新开一个事务)
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            cityMapper.insert(list);
            int a = 1 / 0;
        } catch (Exception e) {
            log.error("addTestTransactional error!", e);
            transactionManager.rollback(status);
        }
    }
}
