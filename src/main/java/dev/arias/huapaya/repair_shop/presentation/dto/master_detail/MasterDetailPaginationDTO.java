package dev.arias.huapaya.repair_shop.presentation.dto.master_detail;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterDetailPaginationDTO {

    private Long id;

    private String description;

    private String value;

    public MasterDetailPaginationDTO(MasterDetailEntity detail) {
        this.id = detail.getId();
        this.description = detail.getDescription();
        this.value = detail.getValue();
    }

}
