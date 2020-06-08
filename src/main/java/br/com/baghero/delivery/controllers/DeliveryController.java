package br.com.baghero.delivery.controllers;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.services.DeliveryService;
import br.com.baghero.delivery.services.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Delivery", tags = {"delivery"})
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final QrCodeService qrCodeService;


    @PutMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Qr Code para atualizar o status da locação")
    public byte[] generate(@PathVariable String id) {
        final QrCode qrcodefile = qrCodeService.createQrcodefile(id);
        ByteArrayResource resource = new ByteArrayResource(qrcodefile.getData());
        return resource.getByteArray();
    }

}
