package br.com.baghero.delivery.services.impl;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.services.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryServiceMockImpl implements DeliveryService {

    @Override
    public void register(DeliveryRequest request) {
        log.info("gravando nova locação {}", request.getStatus());
    }
}
