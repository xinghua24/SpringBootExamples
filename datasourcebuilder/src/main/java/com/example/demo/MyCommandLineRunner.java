package com.example.demo;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource ds;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));");

        // list tables in the databse
        DatabaseMetaData md = ds.getConnection().getMetaData();
        String[] types = { "TABLE" };
        ResultSet rs = md.getTables(null, null, "%", types);
        while (rs.next()) {
            LOG.info("Table in Database: {}", rs.getString(3));
        }
        LOG.info("datasource: {}", ds.toString());
        LOG.info("jdbcTemplate: {}", jdbcTemplate.toString());
    }
}
