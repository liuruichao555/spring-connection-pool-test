package com.liuruichao.connection.pool.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    @Transactional(rollbackFor = Exception.class)
    public void addTestData() {
        City city = null;
        for (int i = 0; i < 500000000; i++) {
            city = new City();
            city.setName("test_" + i);
            cityMapper.insert(city);
            log.info("add name: {}, i: {}", city.getName(), i);
        }
    }
}
