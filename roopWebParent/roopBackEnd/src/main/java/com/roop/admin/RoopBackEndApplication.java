package com.roop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.roop.common.entity","com.roop.admin.user"})
public class RoopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoopBackEndApplication.class, args);
	}

}
