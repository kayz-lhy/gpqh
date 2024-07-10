package com.gpqh.main;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = "com.gpqh")
@MapperScan(basePackages = "com.gpqh.*.mapper")
public class GpqhMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpqhMainApplication.class, args);
    }

}
