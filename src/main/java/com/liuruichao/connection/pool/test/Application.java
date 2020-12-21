package com.liuruichao.connection.pool.test;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
@Log4j2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private int count = 0;

    @Resource
    private CityService cityService;

    @GetMapping("/")
    public List<City> list() {
        return cityService.list();
    }

    @GetMapping("/count")
    public int count() {
        return count++;
    }

    @GetMapping("/test1")
    public String test1(@RequestParam String name, @RequestParam int age) {
        log.info("name: {}, age: {}", name, age);
        return "success";
    }

    @PostMapping("/test2")
    public String test2(@RequestParam String name, @RequestParam int age) {
        log.info("name: {}, age: {}", name, age);
        return "success";
    }

    @PostMapping("/test3")
    public String test3(@RequestBody City city) {
        log.info("name: {}, age: {}", city.getName(), city.getAge());
        return "success";
    }
}
