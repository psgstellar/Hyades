package com.hyades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
public class DBConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void DBConnectionTest() throws Exception{
        try (Connection con = dataSource.getConnection()){
            System.out.println(con.getMetaData().getURL());
            System.out.println(con.getMetaData().getUserName());
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
