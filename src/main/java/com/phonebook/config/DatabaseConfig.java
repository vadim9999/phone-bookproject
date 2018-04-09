package com.phonebook.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Vadim on 09.04.2018.
 */
@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Bean
    public DataSource dataSource() throws SQLException, URISyntaxException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        }else {
            URI dbUri = new URI(dbUrl);
            String username = "";
            try {
                username = dbUri.getUserInfo().split(":")[0];
            } catch (NullPointerException e) {
                System.err.print(e);
            }
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setUsername(username);
            config.setPassword(password);
             config.setJdbcUrl(config.getJdbcUrl()+"?"+"sslmode=require&amp;sslfactory=org.postgresql.ssl.NonValidatingFactory");
            return new HikariDataSource(config);
}
    }
}