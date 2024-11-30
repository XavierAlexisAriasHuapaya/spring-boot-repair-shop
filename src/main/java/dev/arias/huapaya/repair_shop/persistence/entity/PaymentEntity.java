package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
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
    private DocumentEntity typeOperation;

    @ManyToOne
    @JoinColumn(name = "pettyCashId")
    private PettyCashEntity pettyCash;

    @ManyToOne
    @JoinColumn(name = "saleId")
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "methodPaymentId")
    private MasterDetailEntity methodPayment;

    @ManyToOne
    @JoinColumn(name = "cardTypeId")
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

    private LocalDateTime operationDate;

    private String observation;

    private boolean paid;

    private BigDecimal amount;

    private boolean impactPettyCash;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }
}
