package br.com.baghero.delivery.dtos;

import br.com.baghero.delivery.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DeliveryRequest implements Serializable {
    private final static long serialVersionUID = 1L;

    private String user;
    @NotBlank
    private String location;
    @NotBlank
    private String product;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate start;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate end;
    private String value;
    private DeliveryStatus status;
}
