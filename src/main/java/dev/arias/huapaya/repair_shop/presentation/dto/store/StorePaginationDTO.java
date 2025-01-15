package dev.arias.huapaya.repair_shop.presentation.dto.store;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StorePaginationDTO {

    private Long id;

    private String currency;

    private String name;

    private String address;

    private String phone;

    private String logo;

    private boolean status;

    public StorePaginationDTO(StoreEntity store) {
        this.id = store.getId();
        this.currency = new StringBuilder(store.getCurrency().getDescription())
                .append(" (")
                .append(store.getCurrency().getValue())
                .append(")")
                .toString();
        this.name = store.getName();
        this.address = store.getAddress();
        this.phone = store.getPhone();
        this.logo = store.getLogo();
        this.status = store.isStatus();
    }

}
