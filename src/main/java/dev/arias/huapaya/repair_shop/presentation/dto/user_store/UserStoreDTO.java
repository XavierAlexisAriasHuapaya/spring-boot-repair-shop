package dev.arias.huapaya.repair_shop.presentation.dto.user_store;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserStoreDTO {

    private Long id;

    private StoreEntity store;

    private Boolean checked;

}
