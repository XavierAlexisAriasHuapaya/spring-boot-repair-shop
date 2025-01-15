package dev.arias.huapaya.repair_shop.presentation.dto.supplier;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SupplierFindOneDTO {

    private Long id;

    private MasterDetailEntity typeSupplier;

    private MasterDetailEntity country;

    private String companyName;

    private String identityNumber;

    private String address;

    private String phone;

    private String web;

    private LocalDateTime createdAt;

    private Boolean status;

}
