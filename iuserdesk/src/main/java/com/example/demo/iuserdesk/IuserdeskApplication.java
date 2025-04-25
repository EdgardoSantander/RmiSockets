package com.example.demo.iuserdesk;

import com.example.demo.iuserdesk.presentation.SistemaVotosFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IuserdeskApplication {

	public static void main(String[] args) {

		//SpringApplication.run(IuserdeskApplication.class, args);
		Application.launch(SistemaVotosFx.class,args);
	}

}
