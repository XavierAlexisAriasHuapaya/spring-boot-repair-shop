package dev.arias.huapaya.repair_shop.presentation.dto.sale.details;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SaleDetailPaginationDTO {

    private String product;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal discount;

    private String porcentageDiscount;
}
