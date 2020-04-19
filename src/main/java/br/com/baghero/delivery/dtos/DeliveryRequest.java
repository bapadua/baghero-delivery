package br.com.baghero.delivery.dtos;

import br.com.baghero.delivery.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRequest implements Serializable {
    private final static long serialVersionUID = 1L;

    private String id;
    private String product;
    private LocalDate start;
    private LocalDate end;
    private String value;
    private DeliveryStatus status;
}
