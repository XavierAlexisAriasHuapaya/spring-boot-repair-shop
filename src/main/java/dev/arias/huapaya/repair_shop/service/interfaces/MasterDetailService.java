package dev.arias.huapaya.repair_shop.service.interfaces;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailUpdateDTO;

public interface MasterDetailService {

    public MasterDetailEntity create(MasterDetailCreateDTO data);

    public MasterDetailEntity update(MasterDetailUpdateDTO data, Long id);

    public PageDTO<MasterDetailPaginationDTO> pagination(Long id, Pageable pageable);

    public MasterDetailFindOneDTO findById(Long id);

}
