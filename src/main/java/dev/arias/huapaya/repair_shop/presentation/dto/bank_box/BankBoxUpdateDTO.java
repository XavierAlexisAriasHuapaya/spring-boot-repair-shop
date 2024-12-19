package dev.arias.huapaya.repair_shop.presentation.dto.bank_box;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BankBoxUpdateDTO {

    private StoreEntity store;

    private String name;

}
