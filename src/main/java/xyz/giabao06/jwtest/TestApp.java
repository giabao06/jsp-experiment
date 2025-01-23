package xyz.giabao06.jwtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.giabao06.jwtest.db;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication


public class TestApp { ;
    db test = new db("test.db");
    @RequestMapping("/")
    String home(){
        return "Hello world!";
    }

    @RequestMapping("/testPage")
    String testPage(){
        return "This is a test page";
    }

    @GetMapping(path = "/jsonTest", produces= MediaType.APPLICATION_JSON_VALUE)
    ObjectNode jsonTest(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObj = mapper.createObjectNode();
        jsonObj.put("name", "froggy");
        jsonObj.put("age", "69");
        jsonObj.put("key", "secret");
        return jsonObj;
    }

    @RequestMapping("/dbTest")
    String dbTest(){
        try (Connection dbC = this.test.getConnection(); Statement st = dbC.createStatement();) {
            st.setQueryTimeout(30);
            st.executeUpdate("DROP TABLE IF EXISTS person");
            st.executeUpdate("CREATE TABLE person (id integer, name string)");
            st.executeUpdate("INSERT INTO person values(1, 'shiina mahiru')");
            st.executeUpdate("INSERT INTO person values(2, 'shiina kochiya')");
            return "SQL test success, check database";
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            return "SQL exception occurred, see app console";
        }
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(TestApp.class, args);
    }
}
