package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxFindAllDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;

public interface BankBoxService {

    public BankBoxEntity create(BankBoxCreateDTO bankBox);

    public Optional<BankBoxFindOneDTO> findOne(Long id);

    public List<BankBoxFindAllDTO> findAll();

    public BankBoxEntity update(BankBoxUpdateDTO bankBox, Long id);

    public PageDTO<BankBoxPaginationDTO> pagination(Pageable pageable);

}
