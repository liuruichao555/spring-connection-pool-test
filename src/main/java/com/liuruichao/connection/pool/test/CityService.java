package com.liuruichao.connection.pool.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        List<City> list = new ArrayList<>(1000);
        for (int i = 0; i < 500000000; i++) {
            city = new City();
            city.setName("test_" + i);
            list.add(city);
            if (list.size() == 1000) {
                CityService targetService = ((CityService) AopContext.currentProxy());
                targetService.addTestData(list);
                list.clear();
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTestData(List<City> list) {
        log.debug("list size: {}", list.size());
        cityMapper.insert(list);
    }
}
