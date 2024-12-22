package dev.arias.huapaya.repair_shop.presentation.dto.product;

import java.util.List;

import dev.arias.huapaya.repair_shop.presentation.dto.product_store.ProductStoreUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductMovementUpdateDTO {

    private List<ProductStoreUpdateDTO> productStore;

}
