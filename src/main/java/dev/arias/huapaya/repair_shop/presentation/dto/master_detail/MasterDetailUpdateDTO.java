package dev.arias.huapaya.repair_shop.presentation.dto.master_detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterDetailUpdateDTO {

    private String description;

    private String value;

}
