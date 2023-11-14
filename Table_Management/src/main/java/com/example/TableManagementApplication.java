package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.dao.TableInfoRepository;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.dao", basePackageClasses = TableInfoRepository.class)
@ComponentScan(basePackages ="com.example.dao")
public class TableManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableManagementApplication.class, args);
	}

}
