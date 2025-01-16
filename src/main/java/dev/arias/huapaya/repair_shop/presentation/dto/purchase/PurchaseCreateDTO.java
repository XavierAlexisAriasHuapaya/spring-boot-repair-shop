package dev.arias.huapaya.repair_shop.presentation.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.details.PurchaseDetailCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseCreateDTO {

    private SupplierEntity supplier;

    private MasterDetailEntity orderStatus;

    private StoreEntity store;

    private String observation;

    private LocalDate operationDate;

    private List<PurchaseDetailCreateDTO> purchaseDetails;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

}
