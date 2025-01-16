package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchaseCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchaseFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchasePaginationDTO;

public interface PurchaseService {

    public PurchaseEntity create(PurchaseCreateDTO purchase);

    public PageDTO<PurchasePaginationDTO> pagination(Pageable pageable);

    public Optional<PurchaseFindOneDTO> findOne(Long id);

}
