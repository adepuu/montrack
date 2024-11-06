package com.adepuu.montrack;

import com.adepuu.montrack.infrastructure.config.JwtConfigProperties;
import com.adepuu.montrack.infrastructure.config.RsaKeyConfigProperties;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Log
@SpringBootApplication
@EnableConfigurationProperties({JwtConfigProperties.class, RsaKeyConfigProperties.class})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
