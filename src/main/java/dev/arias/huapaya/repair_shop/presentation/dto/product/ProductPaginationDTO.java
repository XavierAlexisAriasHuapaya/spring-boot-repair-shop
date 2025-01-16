package dev.arias.huapaya.repair_shop.presentation.dto.product;

import java.math.BigDecimal;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductPaginationDTO {

    private Long id;

    private String category;

    private String model;

    private String brand;

    private String name;

    private BigDecimal unitPrice;

    private BigDecimal purchasePrice;

    private Boolean status;

    public ProductPaginationDTO(ProductEntity product) {
        this.id = product.getId();
        this.category = product.getCategory().getDescription();
        this.model = product.getModel().getDescription();
        this.brand = product.getBrand().getDescription();
        this.name = product.getName();
        this.unitPrice = product.getUnitPrice();
        this.purchasePrice = product.getPurchasePrice();
        this.status = product.getStatus();
    }

}
