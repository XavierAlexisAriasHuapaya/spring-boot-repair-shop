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
    @JoinColumn(name = "destinationStoreId", nullable = true)
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

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "movementId")
    private List<InboundEntity> inbound;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "movementId")
    private List<OutboundEntity> outbound;

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
        BigDecimal inboundTotal = BigDecimal.ZERO;
        if (this.getReason().getValue().equals("I")) {
            for (InboundEntity inbound : this.getInbound()) {
                BigDecimal quantity = BigDecimal.valueOf(inbound.getQuantity());
                BigDecimal salePrice = inbound.getSalePrice() != null
                        && inbound.getSalePrice().compareTo(BigDecimal.ZERO) != 0
                                ? inbound.getSalePrice()
                                : inbound.getProduct().getUnitPrice();
                inboundTotal = inboundTotal.add(quantity.multiply(salePrice));
            }
        }
        BigDecimal subTotal = inboundTotal.divide(BigDecimal.valueOf(1.18), 2, RoundingMode.HALF_UP);
        this.setSubTotal(this.getReason().getValue().equals("I") ? subTotal : BigDecimal.ZERO);
        this.setMovementTotal(this.getReason().getValue().equals("I") ? inboundTotal : BigDecimal.ZERO);
    }

}
