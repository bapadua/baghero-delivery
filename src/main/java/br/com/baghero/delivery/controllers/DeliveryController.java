package br.com.baghero.delivery.controllers;

import br.com.baghero.delivery.dtos.DeliveryRequest;
import br.com.baghero.delivery.services.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("delivery")
@Api(value = "Delivery", tags = {"delivery"})
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @ApiOperation(value = "Registra uma nova locação")
    public ResponseEntity<?> register(@RequestBody DeliveryRequest request) {
        try {
            log.info("\n {} \n",request.toString());
            deliveryService.register(request);
            log.info("registro gravado {}", request.getProduct());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("erro {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
