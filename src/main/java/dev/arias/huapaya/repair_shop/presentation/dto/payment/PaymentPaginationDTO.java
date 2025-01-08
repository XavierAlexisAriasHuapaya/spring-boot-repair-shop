package dev.arias.huapaya.repair_shop.presentation.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PaymentPaginationDTO {

        private Long id;

        private String type;

        private String typeOperation;

        private String sale;

        private String methodPayment;

        private String creditNote;

        private String purchaseBill;

        private String saleBill;

        private String store;

        private LocalDate operationDate;

        private String paid;

        private BigDecimal taxAmount;

        private BigDecimal subTotal;

        private BigDecimal amount;

        public PaymentPaginationDTO(PaymentEntity payment) {
                this.id = payment.getId();
                this.type = payment.getTypeOperation().getValue().equals("I") ? "INGRESO" : "EGRESO";
                this.typeOperation = payment.getTypeOperation().getDescription();
                this.sale = payment.getSale() == null ? "-"
                                : new StringBuilder(payment.getSale().getSerie())
                                                .append("-")
                                                .append(payment.getSale().getNumber().toString()).toString();
                this.methodPayment = payment.getMethodPayment().getDescription().contains("TARJETA")
                                ? new StringBuilder(payment.getMethodPayment().getDescription())
                                                .append("-")
                                                .append(payment.getCardType().getDescription()).toString()
                                : payment.getMethodPayment().getDescription();
                this.creditNote = payment.getCreditNote() == null ? "-"
                                : new StringBuilder(payment.getCreditNote().getSerie())
                                                .append("-")
                                                .append(payment.getCreditNote().getNumber().toString()).toString();
                this.purchaseBill = payment.getPurchaseBill() == null ? "-"
                                : new StringBuilder(payment.getPurchaseBill().getSerie())
                                                .append("-")
                                                .append(payment.getPurchaseBill().getNumber().toString()).toString();
                this.saleBill = payment.getSaleBill() == null ? "-"
                                : new StringBuilder(payment.getSaleBill().getSerie())
                                                .append("-")
                                                .append(payment.getSaleBill().getNumber().toString()).toString();
                this.store = payment.getStore().getName();
                this.operationDate = payment.getOperationDate();
                this.paid = payment.getInvoiced() ? "FACTURADO" : "PENDIENTE";
                this.taxAmount = payment.getTaxAmount();
                this.subTotal = payment.getSubTotal();
                this.amount = payment.getAmount();
        }

}
