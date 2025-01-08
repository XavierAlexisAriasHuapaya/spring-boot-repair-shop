package dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill;

import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseBillCreateDTO {

    private DocumentEntity document;

    private PurchaseEntity purchase;

    private String serie;

    private Integer number;

    private LocalDate operationDate;

    private String observation;
    
}
