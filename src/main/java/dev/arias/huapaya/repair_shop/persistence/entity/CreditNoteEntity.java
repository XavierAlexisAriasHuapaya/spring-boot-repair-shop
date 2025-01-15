package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "creditNotes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreditNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "saleBillId")
    private SaleBillEntity saleBill;

    @ManyToOne
    @JoinColumn(name = "movementId")
    private MovementEntity movement;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreEntity store;

    @ManyToOne
    @JoinColumn(name = "typeCreditNoteId")
    private MasterDetailEntity typeCreditNote;

    private String serie;

    private Integer number;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal amount;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    private LocalDate operationDate;

    private String observation;

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

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

    private void calculateTotals() {
        BigDecimal amount = BigDecimal.ZERO;
        for (SaleDetailEntity details : this.getSaleBill().getSale().getSaleDetails()) {
            BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
            amount = amount.add(quantity.multiply(details.getPrice()).subtract(details.getDiscount()));
        }
        this.amount = amount;
        this.subTotal = amount.divide(BigDecimal.valueOf(1.18), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.amount.subtract(this.subTotal);
    }
}
