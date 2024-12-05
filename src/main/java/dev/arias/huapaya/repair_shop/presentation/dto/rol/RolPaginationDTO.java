package dev.arias.huapaya.repair_shop.presentation.dto.rol;

import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolPaginationDTO {

    private Long id;

    private String description;

    private Boolean status;

    public RolPaginationDTO(RolEntity rol) {
        this.id = rol.getId();
        this.description = rol.getDescription();
        this.status = rol.getStatus();
    }

}
