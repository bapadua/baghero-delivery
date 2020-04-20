package br.com.baghero.delivery;

import br.com.baghero.delivery.repository.LocationDeliveryRepository;
import br.com.baghero.delivery.services.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
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
