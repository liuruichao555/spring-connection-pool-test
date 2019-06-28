package com.liuruichao.connection.pool.test;

import java.util.List;

/**
 * CityMapper
 *
 * @author liuruichao
 * Created on 2018/9/14 18:28
 */
public interface CityMapper {
    void insert(List<City> list);

    List<City> list();
}