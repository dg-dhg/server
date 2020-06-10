package net.dhg.jdbc;

import net.dhg.jdbc.modeling.Model;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
class JdbcApplicationTests {

    @Autowired
    private Model model;
    @Autowired
    DataSourceProperties dataSourceProperties;
    @Autowired
    DataSource dataSource;


    @Test
    void contextLoads() {
        System.out.println("debug======>" + dataSource.getClass().getName());
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("debug======>" + connection);
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testDatasource() {
        System.out.println(String.format("debug====>%s", tose()));
    }

    public String tose() {
        String data = dataSourceProperties.getUrl().concat("/").concat(dataSourceProperties.getPassword()).concat("/").concat(dataSourceProperties.getUsername()).concat("/").concat(dataSourceProperties.getDriverClassName());
        return data;
    }

}
