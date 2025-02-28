package dev.arias.huapaya.repair_shop.presentation.dto.master;

import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailPaginationDTO;
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

    private PageDTO<MasterDetailPaginationDTO> master_details;

    private Boolean status;
}
