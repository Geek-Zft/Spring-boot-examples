package com.zft;

import com.zft.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        userService.insert("geek-z",20);
    }
}
