package dev.arias.huapaya.repair_shop.presentation.dto.master;

import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterFindOneDTO {
    private Long id;

    private String description;

    private List<MasterDetailEntity> master_details;

    private Boolean status;
}
