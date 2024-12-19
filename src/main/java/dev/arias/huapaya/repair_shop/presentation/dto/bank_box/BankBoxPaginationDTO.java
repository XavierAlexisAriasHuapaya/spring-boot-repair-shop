package dev.arias.huapaya.repair_shop.presentation.dto.bank_box;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BankBoxPaginationDTO {

    private Long id;

    private StoreEntity store;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

    public BankBoxPaginationDTO(BankBoxEntity bankBox) {
        this.id = bankBox.getId();
        this.store = bankBox.getStore();
        this.name = bankBox.getName();
        this.createdAt = bankBox.getCreatedAt();
        this.updatedAt = bankBox.getUpdatedAt();
        this.status = bankBox.getStatus();
    }

}
