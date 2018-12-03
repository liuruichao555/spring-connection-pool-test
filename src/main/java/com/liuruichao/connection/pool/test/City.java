package com.liuruichao.connection.pool.test;

import lombok.Data;

/**
 * City
 *
 * @author liuruichao
 * Created on 2018/9/14 18:27
 */
@Data
public class City {
    /** 城市ID */
    private Long id;

    /** 城市名称 */
    private String name;

    /** 城市别名 */
    private String aliasName;

    /** 省份ID */
    private Integer provincesId;

    /** 得分1 */
    private Integer score1;

    /** 得分2 */
    private Integer score2;

    /** 是否删除 */
    private Integer isDelete = 0;
}
