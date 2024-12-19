package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.PettyCashEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.PettyCashRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.PettyCashService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PettyCashImplementation implements PettyCashService {

    private final PettyCashRepository repository;

    @Override
    public PettyCashEntity create(PettyCashCreateDTO data) {
        PettyCashEntity pettyCashCreate = PettyCashEntity.builder()
                .bankBox(data.getBankBox())
                .openingObservation(data.getOpeningObservation())
                .openingDate(data.getOpeningDate())
                .openingAmount(data.getOpeningAmount())
                .exchangeRate(data.getExchangeRate())
                .build();
        return this.repository.save(pettyCashCreate);
    }

    @Override
    public PettyCashEntity update(PettyCashUpdateDTO data, Long id) {
        Optional<PettyCashEntity> pettyCashFind = this.repository.findById(id);
        if (!pettyCashFind.isPresent()) {

        }
        PettyCashEntity pettyCash = pettyCashFind.get();
        pettyCash.setClosingDate(data.getClosingDate());
        pettyCash.setClosingObservation(data.getClosingObservation());
        pettyCash.setCashClosing(data.getCashClosing());
        pettyCash.setOtherClosing(data.getOtherClosing());
        return this.repository.save(pettyCash);
    }

    @Override
    public Optional<PettyCashFindOneDTO> findOne(Long id) {
        Optional<PettyCashEntity> pettyCashFind = this.repository.findById(id);
        if (!pettyCashFind.isPresent()) {
            return Optional.empty();
        }
        PettyCashEntity pettyCash = pettyCashFind.get();
        PettyCashFindOneDTO dto = PettyCashFindOneDTO.builder()
                .id(id)
                .bankBox(pettyCash.getBankBox())
                .openingObservation(pettyCash.getOpeningObservation())
                .closingObservation(pettyCash.getClosingObservation())
                .openingDate(pettyCash.getOpeningDate())
                .closingDate(pettyCash.getClosingDate())
                .openingAmount(pettyCash.getOpeningAmount())
                .cashClosing(pettyCash.getCashClosing())
                .otherClosing(pettyCash.getOtherClosing())
                .openPettyCash(pettyCash.getOpenPettyCash())
                .exchangeRate(pettyCash.getExchangeRate())
                .createdAt(pettyCash.getCreatedAt())
                .status(pettyCash.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<PettyCashPaginationDTO> pagination(Pageable pageable) {
        Page<PettyCashEntity> pettyCashPage = this.repository.findAll(pageable);
        List<PettyCashPaginationDTO> pettyCashDTO = pettyCashPage.getContent()
                .stream()
                .map(pettyCash -> new PettyCashPaginationDTO(pettyCash))
                .toList();
        return new PageDTO<>(pettyCashDTO, pettyCashPage.getNumber(), pettyCashPage.getSize(),
                pettyCashPage.getTotalElements());
    }

}
