package ru.copypaste.paste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PasteBoxApplication {
	public static void main(String[] args) {
		SpringApplication.run(PasteBoxApplication.class, args);
	}
}
