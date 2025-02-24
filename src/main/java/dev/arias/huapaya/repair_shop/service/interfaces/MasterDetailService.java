package dev.arias.huapaya.repair_shop.service.interfaces;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailUpdateDTO;

public interface MasterDetailService {

    public MasterDetailEntity create(MasterDetailCreateDTO data);

    public MasterDetailEntity update(MasterDetailUpdateDTO data, Long id);

}
