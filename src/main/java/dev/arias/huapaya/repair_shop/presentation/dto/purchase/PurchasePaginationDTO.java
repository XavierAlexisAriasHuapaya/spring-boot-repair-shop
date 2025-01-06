package dev.arias.huapaya.repair_shop.presentation.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchasePaginationDTO {

    private Long id;

    private SupplierEntity supplier;

    private MasterDetailEntity orderStatus;

    private StoreEntity store;

    private LocalDate operationDate;

    private List<PurchaseDetailEntity> purchaseDetails;

    private BigDecimal purchaseAmount;

    public PurchasePaginationDTO(PurchaseEntity purchase) {
        this.id = purchase.getId();
        this.supplier = purchase.getSupplier();
        this.orderStatus = purchase.getOrderStatus();
        this.store = purchase.getStore();
        this.operationDate = purchase.getOperationDate();
        this.purchaseDetails = purchase.getPurchaseDetails();
        this.purchaseAmount = purchase.getPurchaseAmount();
    }

}
