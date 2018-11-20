package com.liuruichao.connection.pool.test;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
}
