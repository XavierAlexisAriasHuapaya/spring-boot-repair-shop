package dev.arias.huapaya.repair_shop.presentation.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseFindOneDTO {

    private Long id;

    private SupplierEntity supplier;

    private MasterDetailEntity orderStatus;

    private StoreEntity store;

    private String observation;

    private LocalDate operationDate;

    private List<PurchaseDetailEntity> purchaseDetails;

    private BigDecimal exchangeRate;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal purchaseAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

}
