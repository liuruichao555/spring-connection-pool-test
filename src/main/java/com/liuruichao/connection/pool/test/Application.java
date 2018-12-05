package com.liuruichao.connection.pool.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Application
 *
 * @author liuruichao
 * Created on 2018/9/14 18:17
 */
@SpringBootApplication
@MapperScan(basePackages = "com.liuruichao.connection.pool.test")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private CityService cityService;

    @GetMapping("/")
    public List<City> list() {
        return cityService.list();
    }
}
