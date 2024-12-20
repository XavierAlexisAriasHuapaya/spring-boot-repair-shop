package dev.arias.huapaya.repair_shop.presentation.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductPaginationDTO {

    private Long id;

    private MasterDetailEntity category;

    private MasterDetailEntity model;

    private MasterDetailEntity brand;

    private String name;

    private BigDecimal unitPrice;

    private BigDecimal purchasePrice;

    private LocalDateTime updatedAt;

    private Boolean status;

    public ProductPaginationDTO(ProductEntity product) {
        this.id = product.getId();
        this.category = product.getCategory();
        this.model = product.getModel();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.unitPrice = product.getUnitPrice();
        this.purchasePrice = product.getPurchasePrice();
        this.updatedAt = product.getUpdatedAt();
        this.status = product.getStatus();
    }

}
