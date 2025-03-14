package dev.arias.huapaya.repair_shop.presentation.dto.store;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreAllDTO {

    private Long id;

    private MasterDetailEntity currency;

    private String name;

    private String address;

    private String phone;

    private String logo;

    private boolean status;

}
