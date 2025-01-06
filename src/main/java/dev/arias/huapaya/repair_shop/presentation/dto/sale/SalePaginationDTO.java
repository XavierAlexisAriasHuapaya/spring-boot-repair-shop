package dev.arias.huapaya.repair_shop.presentation.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SalePaginationDTO {

    private Long id;

    private String document;

    private String paymentCondition;

    private String deliveryStatus;

    private String client;

    private LocalDate operationDate;

    private List<SaleDetailEntity> saleDetails;

    private BigDecimal saleAmount;

    public SalePaginationDTO(SaleEntity sale) {
        this.id = sale.getId();
        this.document = new StringBuilder()
                .append(sale.getDocument().getName())
                .append(" ")
                .append(sale.getSerie())
                .append("-")
                .append(sale.getNumber().toString())
                .toString();
        this.paymentCondition = sale.getPaymentCondition().getDescription();
        this.deliveryStatus = sale.getDeliveryStatus().getDescription();
        this.client = new StringBuilder()
                .append(sale.getClient().getFirstName())
                .append(" ")
                .append(sale.getClient().getLastName())
                .toString();
        this.operationDate = sale.getOperationDate();
        this.saleDetails = sale.getSaleDetails();
        this.saleAmount = sale.getSaleAmount();
    }

}