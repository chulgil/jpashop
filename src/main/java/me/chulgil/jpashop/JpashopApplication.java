package me.chulgil.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();
		System.out.println("data = " + data);
		System.out.println("==================");

		SpringApplication app = new SpringApplication(JpashopApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

}
