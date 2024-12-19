package dev.arias.huapaya.repair_shop.presentation.dto.supplier;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SupplierPaginationDTO {

    private Long id;

    private MasterDetailEntity typeSupplier;

    private String companyName;

    private String identityNumber;

    private String address;

    private String phone;

    private String web;

    private Boolean status;

    public SupplierPaginationDTO(SupplierEntity supplier) {
        this.id = supplier.getId();
        this.typeSupplier = supplier.getTypeSupplier();
        this.companyName = supplier.getCompanyName();
        this.identityNumber = supplier.getIdentityNumber();
        this.address = supplier.getAddress();
        this.phone = supplier.getPhone();
        this.web = supplier.getWeb();
        this.status = supplier.getStatus();
    }

}
