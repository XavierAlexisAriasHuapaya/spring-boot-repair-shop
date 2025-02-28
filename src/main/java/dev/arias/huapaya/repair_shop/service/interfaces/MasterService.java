package dev.arias.huapaya.repair_shop.service.interfaces;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.presentation.dto.master.MasterFindOneDTO;

public interface MasterService {

    public MasterFindOneDTO findOne(Pageable pageable, Long id);
    
}
