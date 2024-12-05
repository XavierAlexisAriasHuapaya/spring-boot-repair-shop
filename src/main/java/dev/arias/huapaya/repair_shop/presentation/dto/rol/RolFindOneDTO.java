package dev.arias.huapaya.repair_shop.presentation.dto.rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RolFindOneDTO {
    
    private Long id;

    private String description;

    private Boolean status;

}
