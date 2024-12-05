package dev.arias.huapaya.repair_shop.presentation.dto.store;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreCreateDTO {

    private MasterDetailEntity currency;

    private String name;

    private String address;

    private String phone;

    private String logo;

}
