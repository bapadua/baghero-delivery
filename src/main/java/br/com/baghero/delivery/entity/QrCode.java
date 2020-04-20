package br.com.baghero.delivery.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "qrcode")
public class QrCode {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fileName;
	private String fileType;
	private String token;
	@JoinColumn(name = "qrcode_id")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "qrCode", fetch = FetchType.LAZY, optional = true)
	private LocationDelivery location;
	
	@Lob
	private byte[] data;
	
	public QrCode() {
	}
	
	public QrCode(String fileName, String token, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

}
