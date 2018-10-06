package com.common.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({ "com.common.*"})
@ImportResource({"classpath:./spring/*-context.xml" })
public class CoreLauncher {

	public static void main(String[] args) {
		SpringApplication.run(CoreLauncher.class, args);
	}
}
