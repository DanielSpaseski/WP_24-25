package com.example.wp_av_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WpAvProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpAvProjectApplication.class, args);
	}

}
