package dev.arias.huapaya.repair_shop.service.interfaces;

import dev.arias.huapaya.repair_shop.presentation.dto.master.MasterFindOneDTO;

public interface MasterService {
    public MasterFindOneDTO findOne(Long id);
}
