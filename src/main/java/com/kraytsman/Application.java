package com.kraytsman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.kraytsman.entity")
@EnableJpaRepositories(basePackages = "com.kraytsman.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.kraytsman.repository.elasticsearch")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
