package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierUpdateDTO;

public interface SupplierService {

    public SupplierEntity create(SupplierCreateDTO data);

    public SupplierEntity update(SupplierUpdateDTO data, Long id);

    public Optional<SupplierFindOneDTO> findOne(Long id);

    public PageDTO<SupplierPaginationDTO> pagination(Pageable pageable);

}
