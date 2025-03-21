package dev.arias.huapaya.repair_shop.presentation.dto.purchase.details;

import java.math.BigDecimal;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchaseDetailCreateDTO {

    private ProductEntity product;

    private Integer quantity;

    private BigDecimal price;

}
