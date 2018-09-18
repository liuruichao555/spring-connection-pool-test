package com.liuruichao.connection.pool.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CityMapper
 *
 * @author liuruichao
 * Created on 2018/9/14 18:28
 */
public interface CityMapper {
    void insert(List<City> list);
}