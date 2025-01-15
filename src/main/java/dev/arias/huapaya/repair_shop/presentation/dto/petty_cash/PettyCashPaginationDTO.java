package dev.arias.huapaya.repair_shop.presentation.dto.petty_cash;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.PettyCashEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PettyCashPaginationDTO {

    private Long id;

    private String bankBox;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private BigDecimal openingAmount;

    private BigDecimal cashClosing;

    private BigDecimal otherClosing;

    private String statusPettyCash;

    private Boolean status;

    public PettyCashPaginationDTO(PettyCashEntity pettyCash) {
        this.id = pettyCash.getId();
        this.bankBox = pettyCash.getBankBox().getName();
        this.openingDate = pettyCash.getOpeningDate();
        this.closingDate = pettyCash.getClosingDate();
        this.openingAmount = pettyCash.getOpeningAmount();
        this.cashClosing = pettyCash.getCashClosing();
        this.otherClosing = pettyCash.getOtherClosing();
        this.statusPettyCash = pettyCash.getOpenPettyCash() ? "OPENING" : "CLOSING";
        this.status = pettyCash.getStatus();
    }

}
