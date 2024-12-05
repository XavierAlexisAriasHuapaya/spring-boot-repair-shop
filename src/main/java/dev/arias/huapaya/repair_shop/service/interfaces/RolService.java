package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolUpdateDTO;

public interface RolService {

    public RolEntity create(RolCreateDTO rol);

    public Optional<RolFindOneDTO> findOne(Long id);

    public RolEntity update(RolUpdateDTO rol, Long id);

    public PageDTO<RolPaginationDTO> pagination(Pageable pageable);

}
