package br.com.baghero.delivery.services.impl;

import br.com.baghero.delivery.entity.QrCode;
import br.com.baghero.delivery.repository.LocationDeliveryRepository;
import br.com.baghero.delivery.repository.QrCodeRepository;
import br.com.baghero.delivery.services.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

	private static final String IMG_EXTENSION = ".png";
	private static final String PNG = "png";

	@Value("${qrcode.path}")
	private String imagePath;

	private final QrCodeRepository qrCodeRepository;
	private final LocationDeliveryRepository locationDeliveryRepository;

	@Override
	public QrCode createQrcodefile(String locationId) {
		String filePath = imagePath.concat(locationId)
				.concat(IMG_EXTENSION);

		int size = 250;
		File qrCode = new File(filePath);
		QrCode file = null;
		try {

			Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.MARGIN, 1);
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(locationId, BarcodeFormat.QR_CODE, size, size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			
			ImageIO.write(image, PNG, qrCode);
			file = storeFile(convert2Multifile(locationId, filePath), locationId);
		} catch (WriterException | IOException e) {
			log.error("qrcode error {}", e.getMessage());
		}

		try {
			qrCode.delete();
			log.info("temp file deleted successfully");
		} catch (Exception e) {
			log.error("erro ao deletar arquivo {}", e.getMessage());
		}
		return file;

	}

	private MockMultipartFile convert2Multifile(String token, String filePath) throws IOException {
		Path path = Paths.get(filePath);
		byte[] data = Files.readAllBytes(path);
		return new MockMultipartFile(token, data);
	}

	public QrCode storeFile(MultipartFile file, String token) {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		try {
			if (fileName.contains("..")) {
				throw new RuntimeException("nome de arquivo invalido");
			}

			QrCode qrCode = new QrCode(fileName, token, PNG, file.getBytes());
			qrCode.setToken(token);
			return qrCodeRepository.save(qrCode);
		} catch (IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}
}
