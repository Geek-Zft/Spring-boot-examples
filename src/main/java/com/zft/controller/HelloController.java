package com.zft.controller;

import com.zft.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    /**
     * 自定义错误页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/hello1")
    public String hello1() throws Exception {
        throw new Exception("发生错误");
    }

    /**
     * 自定义错误返回json格式
     * @return
     * @throws MyException
     */
    @RequestMapping("/hello2")
    public String hello2() throws MyException {
        throw new MyException("发生错误");
    }
}
