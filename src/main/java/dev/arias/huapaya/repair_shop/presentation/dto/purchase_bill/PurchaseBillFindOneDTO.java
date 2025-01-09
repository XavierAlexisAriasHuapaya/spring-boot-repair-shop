package dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseBillFindOneDTO {

    private Long id;

    private DocumentEntity document;

    private PurchaseEntity purchase;

    private String serie;

    private Integer number;

    private LocalDate operationDate;

    private String observation;

    private List<PaymentEntity> payments;

    private LocalDateTime createdAt;

    private Boolean status;

}
