package dev.arias.huapaya.repair_shop.presentation.dto.business;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessFindOneDTO {

    private Long id;

    private MasterDetailEntity country;

    private String companyName;

    private String phone;

    private String address;
    
}
