package xyz.giabao06.jwtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


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
        try (Connection dbC = this.test.getConnection(); Statement st = dbC.createStatement()) {
            st.setQueryTimeout(30);
            st.executeUpdate("DROP TABLE IF EXISTS person");
            st.executeUpdate("CREATE TABLE person (id integer, name string)");
            st.executeUpdate("INSERT INTO person values(1, 'shiina mahiru')");
            st.executeUpdate("INSERT INTO person values(2, 'shiina kochiya')");
            ResultSet rs = st.executeQuery("SELECT * FROM person");
            StringBuilder s = new StringBuilder("SQL test success.\n");
            while (rs.next()) {
                s.append(String.format("id = %d, name=%s\n", rs.getInt("id"), rs.getString("name")));
            }
            return s.toString();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            return "SQL exception occurred, see app console";
        }
    }

    public static void main(String[] args){
        SpringApplication.run(TestApp.class, args);
    }
}
