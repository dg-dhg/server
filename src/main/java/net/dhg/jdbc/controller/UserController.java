package net.dhg.jdbc.controller;

import net.dhg.jdbc.entity.User;
import net.dhg.jdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;


    @GetMapping("/list")
    public List<User> queryAll(HttpServletRequest request) {
        List<User> queryResult = userMapper.queryAll();
        System.out.println(queryResult);
        return queryResult;
    }

    @GetMapping("/query/{id}")
    public User queryById(@PathVariable("id")Integer id){
        User queryResult = userMapper.queryById(id);
        return queryResult;
    }

    @GetMapping("/add")
    public Integer addUser(HttpServletRequest request, HttpServletResponse response){
        User preparedUser = new User();
        preparedUser.setDescription("超管");
        preparedUser.setName("routine");
        preparedUser.setPassword("123123");
        Integer addResult =userMapper.addUser(preparedUser);
        return addResult;

    }

    @GetMapping("/delete/{id}")
    public Integer deleteUser(@PathVariable("id")Integer id){
        Integer deleteResult = userMapper.deleteUser(id);
        return deleteResult;
    }

}
