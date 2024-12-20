package dev.arias.huapaya.repair_shop.presentation.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductFindOneDTO {

    private Long id;

    private MasterDetailEntity unitOfMeasure;

    private MasterDetailEntity category;

    private MasterDetailEntity model;

    private MasterDetailEntity brand;

    private String name;

    private BigDecimal unitPrice;

    private BigDecimal purchasePrice;

    private Integer minimumStock;

    private String image;

    private List<ProductStoreEntity> productStore;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

}
