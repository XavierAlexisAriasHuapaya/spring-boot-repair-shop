package dev.arias.huapaya.repair_shop.presentation.dto.credit_note;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.CreditNoteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreditNotePaginationDTO {

    private Long id;

    private String document;

    private String saleBill;

    private String client;

    private String store;

    private String typeCreditNote;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal amount;

    private LocalDate operationDate;

    private LocalDateTime createdAt;

    private Boolean status;

    public CreditNotePaginationDTO(CreditNoteEntity creditNote) {
        this.id = creditNote.getId();
        this.document = new StringBuilder()
                .append(creditNote.getDocument().getName())
                .append(" ")
                .append(creditNote.getSerie())
                .append("-")
                .append(creditNote.getNumber().toString())
                .toString();
        this.client = new StringBuilder(creditNote.getClient().getFirstName())
                .append(" ")
                .append(creditNote.getClient().getLastName()).toString();
        this.store = creditNote.getStore().getName();
        this.typeCreditNote = creditNote.getTypeCreditNote().getDescription();
        this.taxAmount = creditNote.getAmount();
        this.subTotal = creditNote.getSubTotal();
        this.amount = creditNote.getAmount();
        this.operationDate = creditNote.getOperationDate();
        this.createdAt = creditNote.getCreatedAt();
        this.status = creditNote.getStatus();
    }

}
