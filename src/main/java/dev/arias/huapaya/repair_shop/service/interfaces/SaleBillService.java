package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillPaginationDTO;

public interface SaleBillService {

    public SaleBillEntity create(SaleBillCreateDTO saleBill);

    public PageDTO<SaleBillPaginationDTO> pagination(Pageable pageable);

    public Optional<SaleBillFindOneDTO> findOne(Long id);

}
