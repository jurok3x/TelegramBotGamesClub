package com.ykotsiuba.soloveibot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SoloveibotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoloveibotApplication.class, args);
	}

}
