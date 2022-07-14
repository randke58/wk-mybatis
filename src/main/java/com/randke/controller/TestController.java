package com.randke.controller;

import com.randke.mapper.test1.BlogMapper;
import com.randke.mapper.test2.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UserMapper userMapper;

    @RequestMapping
    public void test(){
        blogMapper.select(1);
        blogMapper.select(1);

    }
}
