package net.dhg.jdbc.mapper;

import net.dhg.jdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/*@mapper means this is a mybatis mapper class*/
@Mapper
/*表示这是dao层*/
@Repository
public interface UserMapper {
    /*增*/
    Integer addUser(User user);
    /*删除*/
    Integer deleteUser(Integer id);
    /*改*/
    Integer updateUser(User user);
    /*查询所有*/
    List<User> queryAll();

    /*查询一个*/
    User queryById(Integer id);
}
