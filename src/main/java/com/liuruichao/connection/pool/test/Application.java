package com.liuruichao.connection.pool.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

/**
 * Application
 *
 * @author liuruichao
 * Created on 2018/9/14 18:17
 */
@SpringBootApplication
@MapperScan(basePackages = "com.liuruichao.connection.pool.test")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        cityService.addTestData();
    }
}
