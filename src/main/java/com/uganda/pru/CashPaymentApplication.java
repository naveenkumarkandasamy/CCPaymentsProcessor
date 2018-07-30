package com.uganda.pru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.uganda.pru"})
public class CashPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashPaymentApplication.class, args);
	}
}
