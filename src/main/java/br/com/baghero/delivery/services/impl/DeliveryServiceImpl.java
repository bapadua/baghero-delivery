package br.com.baghero.delivery.services.impl;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.entity.LocationDelivery;
import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.repository.LocationDeliveryRepository;
import br.com.baghero.delivery.services.DeliveryService;
import br.com.baghero.delivery.services.QrCodeService;
import br.com.baghero.delivery.token.TOTP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final LocationDeliveryRepository repository;
    private final QrCodeService qrCodeService;

    public DeliveryServiceImpl(LocationDeliveryRepository repository, QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
        this.repository = repository;
    }

    @Override
    public String register(DeliveryRequest request) {
        log.info("gravando nova locação {}", request.getStatus());
        final QrCode qrCode = qrCodeService.createQrcodefile(TOTP.getToken(request.getId()));
        return repository.save(mapper(request, qrCode)).getId();
    }

    private LocationDelivery mapper(DeliveryRequest request, QrCode qrCode) {
        return LocationDelivery.builder()
                .id(request.getId())
                .createdAt(LocalDateTime.now())
                .product(request.getProduct())
                .start(request.getStart())
                .end(request.getEnd())
                .status(request.getStatus())
                .value(request.getValue())
                .qrCode(qrCode)
                .build();
    }
}
