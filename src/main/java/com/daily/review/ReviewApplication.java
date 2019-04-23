package com.daily.review;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages = {"com.daily.*"})
@MapperScan(basePackages={"com.daily.service", "com.daily.dao"})
public class ReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewApplication.class, args);
    }

}
