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

    private Long number;

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
        this.generatedSerieAndNumber();
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
        this.subTotal = amount.divide(this.getTax().add(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        this.taxAmount = this.amount.subtract(this.subTotal);
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
}
