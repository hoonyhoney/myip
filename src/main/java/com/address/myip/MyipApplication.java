package com.address.myip;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.address.myip.app.mapper"
})
public class MyipApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyipApplication.class, args);
    }

}
