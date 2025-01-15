package dev.arias.huapaya.repair_shop.presentation.dto.sale_bill;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SaleBillPaginationDTO {

    private Long id;

    private String sale;

    private String document;

    private String client;

    private LocalDate operationDate;

    private BigDecimal amount;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private Boolean status;

    public SaleBillPaginationDTO(SaleBillEntity saleBill) {
        this.id = saleBill.getId();
        this.sale = new StringBuilder()
                .append(saleBill.getSale().getDocument().getName())
                .append(" ")
                .append(saleBill.getSale().getSerie())
                .append("-")
                .append(saleBill.getSale().getNumber().toString())
                .toString();
        this.document = new StringBuilder()
                .append(saleBill.getDocument().getName())
                .append(" ")
                .append(saleBill.getSerie())
                .append("-")
                .append(saleBill.getNumber().toString())
                .toString();
        this.client = new StringBuilder(saleBill.getClient().getFirstName())
                .append(" ")
                .append(saleBill.getClient().getLastName()).toString();
        this.operationDate = saleBill.getOperationDate();
        this.amount = saleBill.getAmount();
        this.taxAmount = saleBill.getTaxAmount();
        this.subTotal = saleBill.getSubTotal();
        this.status = saleBill.getStatus();
    }

}
