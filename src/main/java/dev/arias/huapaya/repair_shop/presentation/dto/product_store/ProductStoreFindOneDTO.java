package dev.arias.huapaya.repair_shop.presentation.dto.product_store;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductStoreFindOneDTO {

    private Long id;

    private StoreEntity store;

    private Integer stock;

    private BigDecimal salePrice;

    private BigDecimal purchasePrice;

    private LocalDateTime createdAt;

    private Boolean status;

}
