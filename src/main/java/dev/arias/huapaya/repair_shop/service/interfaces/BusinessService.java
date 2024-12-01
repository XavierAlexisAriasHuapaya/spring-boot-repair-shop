package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import dev.arias.huapaya.repair_shop.persistence.entity.BusinessEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessUpdateDTO;

public interface BusinessService {

    public BusinessEntity create(BusinessCreateDTO data);

    public BusinessEntity update(BusinessUpdateDTO data, Long id);

    public Optional<BusinessFindOneDTO> findOne(Long id);

}
