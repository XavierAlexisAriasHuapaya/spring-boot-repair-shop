package dev.arias.huapaya.repair_shop.presentation.dto.petty_cash;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PettyCashUpdateDTO {

    private String closingObservation;

    private LocalDate closingDate;

    private BigDecimal cashClosing;

    private BigDecimal otherClosing;

}
