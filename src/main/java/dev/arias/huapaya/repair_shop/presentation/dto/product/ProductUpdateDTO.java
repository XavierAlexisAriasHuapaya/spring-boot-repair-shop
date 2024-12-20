package dev.arias.huapaya.repair_shop.presentation.dto.product;

import java.math.BigDecimal;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductUpdateDTO {

    private MasterDetailEntity unitOfMeasure;

    private MasterDetailEntity category;

    private MasterDetailEntity model;

    private MasterDetailEntity brand;

    private String name;

    private BigDecimal unitPrice;

    private BigDecimal purchasePrice;

    private Integer minimumStock;

    private String image;

}
