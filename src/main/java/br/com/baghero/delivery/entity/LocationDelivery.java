package br.com.baghero.delivery.entity;

import br.com.baghero.delivery.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_DELIVERY")
public class LocationDelivery implements Serializable {

    @Id
    @Column(name = "location_id", unique = true)
    private String location;

    @Column(name = "product_id", nullable = false)
    private String product;

    private LocalDateTime createdAt;
    private LocalDate start;
    private LocalDate end;
    private String value;
    private DeliveryStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qrcode_id")
    private QrCode qrCode;
}
