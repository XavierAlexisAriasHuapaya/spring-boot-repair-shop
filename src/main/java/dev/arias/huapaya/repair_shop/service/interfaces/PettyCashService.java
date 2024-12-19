package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.PettyCashEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashUpdateDTO;

public interface PettyCashService {

    public PettyCashEntity create(PettyCashCreateDTO data);

    public PettyCashEntity update(PettyCashUpdateDTO data, Long id);

    public Optional<PettyCashFindOneDTO> findOne(Long id);

    public PageDTO<PettyCashPaginationDTO> pagination(Pageable pageable);

}
