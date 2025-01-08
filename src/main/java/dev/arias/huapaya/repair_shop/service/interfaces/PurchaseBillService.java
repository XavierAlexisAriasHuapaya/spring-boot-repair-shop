package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBIllPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillFindOneDTO;

public interface PurchaseBillService {

    public PurchaseBillEntity create(PurchaseBillCreateDTO purchaseBill);

    public Optional<PurchaseBillFindOneDTO> findOne(Long id);

    public PageDTO<PurchaseBIllPaginationDTO> pagination(Pageable pageable);

}
