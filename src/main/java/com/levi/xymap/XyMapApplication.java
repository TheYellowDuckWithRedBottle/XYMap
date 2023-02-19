package com.levi.xymap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.levi.xymap.*"})
public class XyMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(XyMapApplication.class, args);
    }
}
