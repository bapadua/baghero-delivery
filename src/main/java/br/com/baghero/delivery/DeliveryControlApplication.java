package br.com.baghero.delivery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryControlApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DeliveryControlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
