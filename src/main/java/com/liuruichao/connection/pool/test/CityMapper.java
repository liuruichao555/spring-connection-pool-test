package com.liuruichao.connection.pool.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * CityMapper
 *
 * @author liuruichao
 * Created on 2018/9/14 18:28
 */
@Mapper
public interface CityMapper {
    @Select("select * from city where state = #{state}")
    City findByState(@Param("state") String state);
}