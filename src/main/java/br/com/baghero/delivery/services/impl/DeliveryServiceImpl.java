package br.com.baghero.delivery.services.impl;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.entity.LocationDelivery;
import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.repository.LocationDeliveryRepository;
import br.com.baghero.delivery.services.DeliveryService;
import br.com.baghero.delivery.services.QrCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final LocationDeliveryRepository repository;
    private final QrCodeService qrCodeService;


    @Override
    public void register(DeliveryRequest request) {
        final QrCode qrCode = qrCodeService.createQrcodefile(request.getLocation());
        final LocationDelivery delivery = mapper(request, qrCode);
        repository.save(delivery);
        log.info("locacao");
    }

    private LocationDelivery mapper(DeliveryRequest request, QrCode qrCode) {
        return LocationDelivery.builder()
                .location(request.getLocation())
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
