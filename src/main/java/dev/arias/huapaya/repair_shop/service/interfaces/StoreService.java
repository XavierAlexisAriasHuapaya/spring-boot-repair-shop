package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StorePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreUpdateDTO;

public interface StoreService {

    public StoreEntity create(StoreCreateDTO data);

    public StoreEntity update(StoreUpdateDTO data, Long id);

    public Optional<StoreFindOneDTO> findOne(Long id);

    public PageDTO<StorePaginationDTO> pagination(Pageable pageable);

}
