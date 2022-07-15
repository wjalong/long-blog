package com.wjalong.longuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wjalong.mapper")
public class LongUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongUserApplication.class, args);
    }

}
