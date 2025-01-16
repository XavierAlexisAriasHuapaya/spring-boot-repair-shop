package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchaseBills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseBillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private PurchaseEntity purchase;

    private String serie;

    private Integer number;

    private LocalDate operationDate;

    private String observation;

    @OneToMany(mappedBy = "purchaseBill", fetch = FetchType.LAZY)
    @JsonManagedReference("purchaseBills-Payments")
    private List<PaymentEntity> payments;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal purchaseBillAmount;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = true;
        this.subTotal = this.getPurchase().getSubTotal();
        this.taxAmount = this.getPurchase().getTaxAmount();
        this.purchaseBillAmount = this.getPurchase().getPurchaseAmount();
        this.exchangeRate = this.getPurchase().getExchangeRate();
        this.tax = this.getPurchase().getTax();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }
}
