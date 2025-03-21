package dev.arias.huapaya.repair_shop.presentation.dto.petty_cash;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PettyCashFindOneDTO {

    private Long id;

    private BankBoxEntity bankBox;

    private String openingObservation;

    private String closingObservation;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

    private BigDecimal openingAmount;

    private BigDecimal cashClosing;

    private BigDecimal otherClosing;

    private Boolean openPettyCash;

    private BigDecimal exchangeRate;

    private LocalDateTime createdAt;

    private Boolean status;

}
