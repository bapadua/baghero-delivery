package br.com.baghero.delivery.controllers;

import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.services.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Delivery", tags = {"delivery"})
@RequiredArgsConstructor
public class DeliveryController {

    private final QrCodeService qrCodeService;

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Qr Code para atualizar o status da locação")
    public byte[] generate(@PathVariable String id) {
        final QrCode qrcodefile = qrCodeService.createQrcodefile(id);
        ByteArrayResource resource = new ByteArrayResource(qrcodefile.getData());
        return resource.getByteArray();
    }

}
