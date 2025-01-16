package dev.arias.huapaya.repair_shop.presentation.dto.purchase.details;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseDetailPagiantionDTO {

    private String product;

    private String description;

    private BigDecimal totalAmount;

}
