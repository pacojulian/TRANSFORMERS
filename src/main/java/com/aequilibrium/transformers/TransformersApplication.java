package com.aequilibrium.transformers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TransformersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransformersApplication.class, args);
    }

}
