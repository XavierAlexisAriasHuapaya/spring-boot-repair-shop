package dev.arias.huapaya.repair_shop.presentation.dto.bank_box;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BankBoxFindAllDTO {

    private Long id;

    private StoreEntity store;

    private String name;

    private LocalDateTime createdAt;

    private Boolean status;

}
