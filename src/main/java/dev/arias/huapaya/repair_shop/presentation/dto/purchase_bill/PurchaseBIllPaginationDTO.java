package dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill;

import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseBIllPaginationDTO {

    private Long id;

    private DocumentEntity document;

    private PurchaseEntity purchase;

    private String serie;

    private Integer number;

    private LocalDate operationDate;

    private String observation;

    private Boolean status;

    public PurchaseBIllPaginationDTO(PurchaseBillEntity purchaseBill) {
        this.id = purchaseBill.getId();
        this.document = purchaseBill.getDocument();
        this.purchase = purchaseBill.getPurchase();
        this.serie = purchaseBill.getSerie();
        this.number = purchaseBill.getNumber();
        this.operationDate = purchaseBill.getOperationDate();
        this.observation = purchaseBill.getObservation();
        this.status = purchaseBill.getStatus();
    }

}
