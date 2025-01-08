package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "typeOperationId")
    private MasterDetailEntity typeOperation;

    @ManyToOne
    @JoinColumn(name = "pettyCashId")
    private PettyCashEntity pettyCash;

    @ManyToOne
    @JoinColumn(name = "saleId", nullable = true)
    @JsonBackReference
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "purchaseId", nullable = true)
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "methodPaymentId", nullable = true)
    private MasterDetailEntity methodPayment;

    @ManyToOne
    @JoinColumn(name = "cardTypeId", nullable = true)
    private MasterDetailEntity cardType;

    @ManyToOne
    @JoinColumn(name = "creditNoteId", nullable = true)
    private CreditNoteEntity creditNote;

    @ManyToOne
    @JoinColumn(name = "purchaseBillId", nullable = true)
    private PurchaseBillEntity purchaseBill;

    @ManyToOne
    @JoinColumn(name = "saleBillId", nullable = true)
    private SaleBillEntity saleBill;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreEntity store;

    private LocalDate operationDate;

    private String observation;

    private Boolean invoiced;

    private BigDecimal exchangeRate;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal amount;

    private Boolean impactPettyCash;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = true;
        this.invoiced = false;
        this.calculateTotals();
        this.paymentSaleOrPurchase();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

    private void calculateTotals() {
        this.subTotal = this.amount.divide(BigDecimal.valueOf(1.18), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.amount.subtract(this.subTotal);
    }

    private void paymentSaleOrPurchase() {
        BigDecimal amountSale = this.getSale().getSaleAmount();
        BigDecimal onAccount = BigDecimal.ZERO;
        BigDecimal pendingAmountSale = BigDecimal.ZERO;
        if (this.getSale().getPayments() != null) {
            for (PaymentEntity payments : this.getSale().getPayments()) {
                onAccount = payments.getStatus() ? onAccount.add(payments.getAmount()) : onAccount.add(BigDecimal.ZERO);
            }
            pendingAmountSale = amountSale.subtract(onAccount);
            if (this.getAmount().compareTo(pendingAmountSale) > 0) {
                throw new ExceptionMessage("The amount is greater than the pending sale");
            }

            this.setPaidSale(false);

            if (this.getAmount().compareTo(pendingAmountSale) == 0) {
                this.setPaidSale(true);
            }
        }
    }

    @Transient
    @JsonIgnore
    private Boolean paidSale;

}
