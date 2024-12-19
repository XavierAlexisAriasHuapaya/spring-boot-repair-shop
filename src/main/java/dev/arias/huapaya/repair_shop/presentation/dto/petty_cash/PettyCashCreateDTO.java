package dev.arias.huapaya.repair_shop.presentation.dto.petty_cash;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PettyCashCreateDTO {

    private BankBoxEntity bankBox;

    private String openingObservation;

    private LocalDate openingDate;

    private BigDecimal openingAmount;

    private BigDecimal exchangeRate;

}
