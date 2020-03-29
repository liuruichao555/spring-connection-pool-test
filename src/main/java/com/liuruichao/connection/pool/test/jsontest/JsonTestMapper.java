package com.liuruichao.connection.pool.test.jsontest;

import com.liuruichao.connection.pool.test.City;

import java.util.List;

/**
 * CityMapper
 *
 * @author liuruichao
 * Created on 2018/9/14 18:28
 */
public interface JsonTestMapper {
    void insert(List<JsonTest> list);

    void insert2(List<JsonTest2> list);
}