package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name = "orderStatusId")
    private MasterDetailEntity orderStatus;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreEntity store;

    private String observation;

    private LocalDate operationDate;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchaseId")
    private List<PurchaseDetailEntity> purchaseDetails;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal purchaseAmount;

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
        this.calculateTotals();
    }

    private void calculateTotals() {
        BigDecimal amount = BigDecimal.ZERO;
        for (PurchaseDetailEntity details : this.purchaseDetails) {
            BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
            amount = amount.add(quantity.multiply(details.getPrice()));
        }
        this.purchaseDetails.stream()
                .forEach(details -> {
                    BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
                    details.setTotalAmount(quantity.multiply(details.getPrice()));
                });
        this.purchaseAmount = amount;
        this.subTotal = amount.divide(this.getTax().add(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.purchaseAmount.subtract(this.subTotal);
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

}
