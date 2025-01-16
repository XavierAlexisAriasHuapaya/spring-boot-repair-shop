package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
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
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "paymentCoditionId")
    private MasterDetailEntity paymentCondition;

    @ManyToOne
    @JoinColumn(name = "pettyCashId")
    private PettyCashEntity pettyCash;

    @ManyToOne
    @JoinColumn(name = "deliveryStatusId")
    private MasterDetailEntity deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreEntity store;

    private String serie;

    private Long number;

    private LocalDate operationDate;

    private LocalDate deliveryDate;

    private LocalDate expirationDate;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "saleId")
    private List<SaleDetailEntity> saleDetails;

    private String observation;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    private BigDecimal saleAmount;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal discount;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    @JsonManagedReference("sale-Payments")
    private List<PaymentEntity> payments;

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
        this.generatedSerieAndNumber();
    }

    private void calculateTotals() {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        for (SaleDetailEntity details : this.saleDetails) {
            BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
            discount = discount.add(details.getDiscount());
            amount = amount.add(quantity.multiply(details.getPrice()).subtract(details.getDiscount()));
        }
        this.saleDetails.stream()
                .forEach(details -> {
                    BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
                    details.setTotalAmount(quantity.multiply(details.getPrice()).subtract(details.getDiscount()));
                });
        this.discount = discount;
        this.saleAmount = amount;
        this.subTotal = amount.divide((this.getTax().add(BigDecimal.ONE)), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.saleAmount.subtract(this.subTotal);
    }

    private void generatedSerieAndNumber() {
        Optional<String> serieOpt = this.getDocument().getDocumentStore().stream()
                .filter(docStore -> docStore.getStore().getId().equals(this.getStore().getId()))
                .map(docStore -> docStore.getSerie())
                .findFirst();
        Optional<Long> numberOpt = this.getDocument().getDocumentStore().stream()
                .filter(docStore -> docStore.getStore().getId().equals(this.getStore().getId()))
                .map(docStore -> docStore.getNumber() + 1)
                .findFirst();
        if (!serieOpt.isPresent() || !numberOpt.isPresent()) {
            throw new ExceptionMessage("Not found serie and number");
        }
        this.serie = serieOpt.get();
        this.number = numberOpt.get();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

}
