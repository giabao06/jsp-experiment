package xyz.giabao06.jwtest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class db{
    private static final HikariConfig cfg = new HikariConfig();
    private static HikariDataSource ds;
    public db(String dbname){
        cfg.setJdbcUrl(String.format("jdbc:sqlite:%s", dbname));
        ds = new HikariDataSource(cfg);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
