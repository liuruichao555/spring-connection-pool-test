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
        City city = null;
        List<City> list = Lists.newArrayListWithCapacity(10000);
        int totalRecords = 500000;
        Random random = new Random();
        for (int i = 0; i < totalRecords; i++) {
            city = new City();
            city.setName("test_" + i);
            city.setProvincesId(random.nextInt(30));
            city.setAliasName("test_alias_" + i);
            list.add(city);
            if (list.size() == 10000) {
                CityService targetService = ((CityService) AopContext.currentProxy());
                targetService.addTestData(list);
                list.clear();
            }
        }

        if (list.size() > 0) {
            CityService targetService = ((CityService) AopContext.currentProxy());
            targetService.addTestData(list);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTestData(List<City> list) {
        cityMapper.insert(list);
    }
}
