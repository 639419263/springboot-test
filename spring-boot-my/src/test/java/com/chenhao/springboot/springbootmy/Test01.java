package com.chenhao.springboot.springbootmy;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Value("${book.name}")
    private String bookName;
    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.desc}")
    private String bookDesc;
    @Value("${test.value}")
    private String randomValue;
    @Value("${test.number}")
    private String randomInt;
    @Value("${test.bignumber}")
    private String number;
    @Value("${test.test1}")
    private String test1;
    @Value("${test.test2}")
    private String test2;

    @Test
    public void show(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("bookName",bookName);
        map.put("bookAuthor",bookAuthor);
        map.put("bookDesc",bookDesc);
        map.put("randomValue",randomValue);
        map.put("randomInt",randomInt);
        map.put("number",number);
        map.put("test1",test1);
        map.put("test2",test2);
        System.out.println(JSON.toJSONString(map));
    }
}
