package br.com.baghero.delivery.controllers;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.services.DeliveryService;
import br.com.baghero.delivery.services.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("delivery")
@Api(value = "Delivery", tags = {"delivery"})
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final QrCodeService qrCodeService;

    public DeliveryController(DeliveryService deliveryService, QrCodeService qrCodeService) {
        this.deliveryService = deliveryService;
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    @ApiOperation(value = "Registra uma nova locação")
    public ResponseEntity<?> register(@RequestBody DeliveryRequest request) {
        try {
            log.info("\n {} \n",request.toString());
            final String register = deliveryService.register(request);
            log.info("registro gravado {}", request.getProduct());
            return new ResponseEntity<>(register, HttpStatus.OK);
        } catch (Exception e) {
            log.error("erro {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Qr Code para atualizar o status da locação")
    public ResponseEntity<byte[]> getQrcode(@PathVariable String id) {
        QrCode qrcode = qrCodeService.getFile(id);
        ByteArrayResource resource = new ByteArrayResource(qrcode.getData());
        return new ResponseEntity<>(resource.getByteArray(), HttpStatus.OK);
    }
}
