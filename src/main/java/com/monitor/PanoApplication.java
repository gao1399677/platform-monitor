package com.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.wit.pano.mapper")
public class PanoApplication {

    public static void main(String[] args) {

        SpringApplication.run(PanoApplication.class, args);
    }

}
