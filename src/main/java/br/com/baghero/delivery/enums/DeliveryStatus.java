package br.com.baghero.delivery.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    EXTRAVIADO("Produto extraviado"),
    PENDENTE("Produto com entrega pendente"),
    PRODUTO_RETIRADO("Produto retirado pelo locatário"),
    PRODUTO_ENTREGUE("Produto devolvido ao locador");

    private String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

}
