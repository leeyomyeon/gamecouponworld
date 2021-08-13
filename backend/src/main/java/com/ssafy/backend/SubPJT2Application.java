package com.ssafy.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.ssafy.backend.**.mapper")
public class SubPJT2Application {

	public static void main(String[] args) {
//		SpringApplication.run(SubPJT2Application.class, args);
		SpringApplication app =new SpringApplication(SubPJT2Application.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}

}
