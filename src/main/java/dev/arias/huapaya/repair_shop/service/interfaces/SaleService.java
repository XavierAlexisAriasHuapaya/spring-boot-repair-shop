package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SaleCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SaleFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SalePaginationDTO;

public interface SaleService {

    public SaleEntity create(SaleCreateDTO sale);

    public PageDTO<SalePaginationDTO> pagination(Pageable pageable);

    public Optional<SaleFindOneDTO> findOne(Long id);

}
