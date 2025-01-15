package dev.arias.huapaya.repair_shop.presentation.dto.business;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessUpdateDTO {

    private MasterDetailEntity country;

    private String companyName;

    private String identityNumber;

    private String phone;

    private String address;
    
}
