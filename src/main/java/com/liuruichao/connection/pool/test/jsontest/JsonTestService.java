package com.liuruichao.connection.pool.test.jsontest;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruichao.liu created on 2020/3/29
 * @version $Id$
 */
@Service
public class JsonTestService {
    @Resource
    private JsonTestMapper jsonTestMapper;

    public void addTestData() {
        int count = 1000;
        List<JsonTest> list = new ArrayList<>();
        List<JsonTest2> list2 = new ArrayList<>();

        for (int i = 0; i < 30000000; i++) {
            JsonTest jsonTest = new JsonTest();
            jsonTest.setName("liuruichao");
            jsonTest.setFirstName("liuruichao");
            jsonTest.setLastName("liuruichao");

            JsonTest2 jsonTest2 = new JsonTest2();
            JsonTest2.NameBean nameBean = new JsonTest2.NameBean();
            nameBean.setName("liuruichao");
            nameBean.setFirstName("liuruichao");
            nameBean.setLastName("liuruichao");
            jsonTest2.setName(JSONObject.toJSONString(nameBean));

            list.add(jsonTest);
            list2.add(jsonTest2);
            if (list.size() % count == 0) {
                jsonTestMapper.insert(list);
                jsonTestMapper.insert2(list2);

                list.clear();
                list2.clear();
            }
        }
    }
}
