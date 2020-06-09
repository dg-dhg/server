package net.dhg.jdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
/*
 * restController就等于ResponseBody+Controller
 * */
@Slf4j
public class JDBCController {
    @Autowired
    JdbcTemplate template;
/*查询所有*/
    @GetMapping("/users")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        /*难道说，jdbc查出来的的东西是map吗*/
        List<Map<String, Object>> list_maps = template.queryForList(sql);
        return list_maps;
    }
/*添加*/
    @GetMapping("/aUser")
    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        String sql = "insert into mybatis.user(name,password,description) values('老邓头','123456','一个大帅哥')";
        template.update(sql);
        try {
//            request.getRequestDispatcher("/users").forward(request, response);
            response.sendRedirect("/users");
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
/*更新*/
    @GetMapping("/partOfUser/{id}")
    public void updateUser(@PathVariable("id")String id,  HttpServletRequest request, HttpServletResponse response){
        String sql="update mybatis.user set  name=?,password=?,description=? where id="+id;

       //分装Object
        Object[] objects=new Object[3];
        objects[0]="邓大大";
        objects[1]="123456";
        objects[2]="已经是风烛残年的老头啦!!";



//除了传递sql语句之外还可以传递参数呦
        template.update(sql,objects);
        try {
            response.sendRedirect("/users");
        }catch (Exception e){
        }
    }

    /*删除*/
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id,HttpServletResponse response,HttpServletRequest request){
        String sql="delete from mybatis.user where id=?";
        template.update(sql,id);

        try {
            response.sendRedirect("/users");
        }catch (Exception e){
        }
    }

}
