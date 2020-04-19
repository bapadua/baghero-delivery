package br.com.baghero.delivery.enums;

public enum DeliveryStatus {
    PENDENTE("Produto com entrega pendente"),
    PRODUTO_RETIRADO("Produto retirado pelo locatário"),
    PRODUTO_ENTREGUE("Produto devolvido ao locador");

    private String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
