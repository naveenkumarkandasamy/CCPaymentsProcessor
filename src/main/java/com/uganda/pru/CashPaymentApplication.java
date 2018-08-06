package com.uganda.pru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@ComponentScan(basePackages = { "com.uganda.pru"})
public class CashPaymentApplication {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
	    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
	    pool.setCorePoolSize(1);
	    pool.setMaxPoolSize(10);
	    pool.setWaitForTasksToCompleteOnShutdown(true);
	    return pool;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CashPaymentApplication.class, args);
	}
}
