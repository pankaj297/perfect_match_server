package com.shaadi.shaadi.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Cors cors = new Cors();
    private Admin admin = new Admin();

    @Data
    public static class Cors {
        private List<String> allowedOrigins = List.of(
                "http://localhost:5173",
                "https://banjaramelava.netlify.app");
    }

    @Data
    public static class Admin {
        private String username = "admin";
        private String password = "admin123"; // override via env in prod
    }
}