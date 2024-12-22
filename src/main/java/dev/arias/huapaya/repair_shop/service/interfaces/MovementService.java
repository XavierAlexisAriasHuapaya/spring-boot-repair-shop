package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementPaginationDTO;

public interface MovementService {

    public MovementEntity create(MovementCreateDTO data);

    public Optional<MovementFindOneDTO> findOne(Long id);

    public PageDTO<MovementPaginationDTO> pagination(Pageable pageable);

}
