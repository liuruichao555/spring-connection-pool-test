package com.liuruichao.connection.pool.test.jsontest;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author ruichao.liu created on 2020/3/29
 * @version $Id$
 */
@Data
public class JsonTest2 implements Serializable {
    private Long id;

    private String name;

    @Data
    public static class NameBean implements Serializable {
        private String name;

        private String firstName;

        private String lastName;
    }
}
