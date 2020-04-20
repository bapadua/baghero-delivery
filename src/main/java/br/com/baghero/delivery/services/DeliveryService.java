package br.com.baghero.delivery.services;

import br.com.baghero.delivery.dtos.DeliveryRequest;

public interface DeliveryService {
    String register(DeliveryRequest request);
}
