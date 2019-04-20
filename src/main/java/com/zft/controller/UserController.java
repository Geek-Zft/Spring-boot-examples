package com.zft.controller;

import com.zft.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * RESTful接口编写demo
 */
@RestController
@RequestMapping("/users")
public class UserController {

    // 创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value="获取user列表", notes="")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，⽤来获取⽤户列表
        // 还可以通过@RequestParam从页⾯中传递参数来进⾏查询条件或者翻页信息的传递
        return new ArrayList<User>(users.values());
    }

    @ApiOperation(value="创建user", notes="根据User对象创建⽤户")
    @ApiImplicitParam(name = "user", value = "⽤户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，⽤来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页⾯中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取user详细信息", notes="根据url的id来获取⽤户详细信息")
    @ApiImplicitParam(name = "id", value = "⽤户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，⽤来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @ApiOperation(value="更新user详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新⽤户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "⽤户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，⽤来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除user", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，⽤来删除User
        users.remove(id);
        return "success";
    }

}
