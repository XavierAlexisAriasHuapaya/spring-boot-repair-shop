package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
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

    private BigDecimal amount;

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
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }
}
