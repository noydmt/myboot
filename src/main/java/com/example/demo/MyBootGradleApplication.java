package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // この class が springboot のアプリケーションクラスであることを示す
public class MyBootGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBootGradleApplication.class, args);
	}

}
