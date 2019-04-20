package com.zft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        // 加⼊⼀个属性，⽤来在模板中读取
        map.addAttribute("host", "http://geek-z.github.io");
        // return模板⽂件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
