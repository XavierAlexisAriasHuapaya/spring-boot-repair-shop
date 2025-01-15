package dev.arias.huapaya.repair_shop.presentation.dto.sale_bill;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SaleBillCreateDTO {

    private SaleEntity sale;

    private DocumentEntity document;

    private ClientEntity client;

    private LocalDate operationDate;

    private BigDecimal amount;

    private BigDecimal tax;

    private String observation;

}
