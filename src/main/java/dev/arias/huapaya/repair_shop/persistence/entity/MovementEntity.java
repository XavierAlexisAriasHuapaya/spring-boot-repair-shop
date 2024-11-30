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
@Table(name = "movements")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reasonId")
    private MasterDetailEntity reason;

    @ManyToOne
    @JoinColumn(name = "originStoreId")
    private StoreEntity originStore;
    
    @ManyToOne
    @JoinColumn(name = "destinationStoreId")
    private StoreEntity destinationStore;

    @ManyToOne
    @JoinColumn(name = "referenceMovementId", nullable = true)
    private MovementEntity referenceMovement;

    @ManyToOne
    @JoinColumn(name = "saleId", nullable = true)
    private SaleEntity sale;

    private LocalDate operationDate;

    private String observation;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal movementTotal;
    
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
