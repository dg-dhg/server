package net.dhg.jdbc.controller;

import net.dhg.jdbc.entity.User;
import net.dhg.jdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
}
