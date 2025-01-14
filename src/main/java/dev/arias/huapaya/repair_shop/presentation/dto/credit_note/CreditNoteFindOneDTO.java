package dev.arias.huapaya.repair_shop.presentation.dto.credit_note;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreditNoteFindOneDTO {

    private Long id;

    private DocumentEntity document;

    private SaleBillEntity saleBill;

    private MovementEntity movement;

    private ClientEntity client;

    private StoreEntity store;

    private MasterDetailEntity typeCreditNote;

    private String serie;

    private Integer number;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal amount;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    private LocalDate operationDate;

    private String observation;

    private Boolean status;

}
