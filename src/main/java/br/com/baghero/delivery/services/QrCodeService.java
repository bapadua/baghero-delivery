package br.com.baghero.delivery.services;

import br.com.baghero.delivery.entity.QrCode;

public interface QrCodeService {

	QrCode createQrcodefile(String locationId);
}
