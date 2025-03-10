package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saleBills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleBillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "saleId")
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    private LocalDate operationDate;

    private String serie;

    private Long number;

    private BigDecimal amount;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    private Boolean advanceAmount;

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
        this.generatedSerieAndNumber();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

    private void calculateTotals() {
        BigDecimal amount = BigDecimal.ZERO;
        for (SaleDetailEntity details : this.sale.getSaleDetails()) {
            BigDecimal quantity = BigDecimal.valueOf(details.getQuantity());
            amount = amount.add(quantity.multiply(details.getPrice()).subtract(details.getDiscount()));
        }
        this.amount = amount;
        this.subTotal = amount.divide((this.getTax().add(BigDecimal.ONE)), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.amount.subtract(this.subTotal);
    }

    private void generatedSerieAndNumber() {
        Optional<String> serieOpt = this.getDocument().getDocumentStore().stream()
                .filter(docStore -> docStore.getStore().getId().equals(this.getSale().getStore().getId()))
                .map(docStore -> docStore.getSerie())
                .findFirst();
        Optional<Long> numberOpt = this.getDocument().getDocumentStore().stream()
                .filter(docStore -> docStore.getStore().getId().equals(this.getSale().getStore().getId()))
                .map(docStore -> docStore.getNumber() + 1)
                .findFirst();
        if (!serieOpt.isPresent() || !numberOpt.isPresent()) {
            throw new ExceptionMessage("Not found serie and number");
        }
        this.serie = serieOpt.get();
        this.number = numberOpt.get();
    }

}
