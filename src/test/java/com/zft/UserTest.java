package com.zft;

import com.zft.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
//声明一个ApplicationContext集成测试加载WebApplicationContext。作用是模拟ServletContext
@WebAppConfiguration
public class UserTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }
    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request = null;
        // 1、get查⼀下user列表，应该为空
        request = get("/users/");
        //perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        // 2、post提交⼀个user
        request = post("/users/")
                //param：添加request的参数
                .param("id", "1")
                .param("name", "测试⼤师")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
        // 3、get获取user列表，应该有刚才插⼊的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试⼤师\",\"age\":20}]")));
        // 4、put修改id为1的user
        request = put("/users/1")
                .param("name", "测试终极⼤师")
                .param("age", "30");
        mvc.perform(request)
                //andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确
                .andExpect(content().string(equalTo("success")));
        // 5、get⼀个id为1的user
        request = get("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极⼤师\",\"age\":30}")));
        // 6、del删除id为1的user
        request = delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
        // 7、get查⼀下user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

}
