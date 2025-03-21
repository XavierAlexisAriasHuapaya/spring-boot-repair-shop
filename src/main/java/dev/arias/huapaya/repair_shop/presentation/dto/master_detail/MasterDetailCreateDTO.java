package dev.arias.huapaya.repair_shop.presentation.dto.master_detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterDetailCreateDTO {

    private Long idMaster;

    private String description;

    private String value;

}
