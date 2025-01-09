package dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseBIllPaginationDTO {

    private Long id;

    private String supplier;

    private String document;

    private String store;

    private LocalDate operationDate;

    private String observation;

    private BigDecimal totalPurchaseAmount;

    private BigDecimal totalPaid;

    private BigDecimal remainingAmount;

    private Boolean status;

    public PurchaseBIllPaginationDTO(PurchaseBillEntity purchaseBill) {
        this.id = purchaseBill.getId();
        this.supplier = purchaseBill.getPurchase().getSupplier().getCompanyName();
        this.document = new StringBuilder(purchaseBill.getSerie())
                .append("-")
                .append(purchaseBill.getNumber())
                .toString();
        this.store = purchaseBill.getPurchase().getStore().getName();
        this.operationDate = purchaseBill.getOperationDate();
        this.observation = purchaseBill.getObservation();
        this.status = purchaseBill.getStatus();
        this.totalPurchaseAmount = purchaseBill.getPurchase().getPurchaseAmount();
        if (purchaseBill.getPayments() != null) {
            BigDecimal account = BigDecimal.ZERO;
            for (PaymentEntity payment : purchaseBill.getPayments()) {
                if (payment.getStatus()) {
                    account = account.add(payment.getAmount());
                }
            }
            this.totalPaid = account;
            this.remainingAmount = this.totalPurchaseAmount.subtract(totalPaid);
        } else {
            this.totalPaid = BigDecimal.ZERO;
            this.remainingAmount = this.totalPurchaseAmount;
        }
    }

}
