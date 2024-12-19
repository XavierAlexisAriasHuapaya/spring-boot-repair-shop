package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.BankBoxRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.BankBoxService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BankBoxImplementation implements BankBoxService {

    private final BankBoxRepository repository;

    @Override
    public BankBoxEntity create(BankBoxCreateDTO bankBox) {
        BankBoxEntity bankBoxCreate = BankBoxEntity.builder()
                .store(bankBox.getStore())
                .name(bankBox.getName())
                .build();
        return this.repository.save(bankBoxCreate);
    }

    @Override
    public Optional<BankBoxFindOneDTO> findOne(Long id) {
        Optional<BankBoxEntity> bankBoxFind = this.repository.findById(id);
        if (!bankBoxFind.isPresent()) {
            return Optional.empty();
        }
        BankBoxEntity bankBox = bankBoxFind.get();
        BankBoxFindOneDTO dto = BankBoxFindOneDTO.builder()
                .id(id)
                .store(bankBox.getStore())
                .name(bankBox.getName())
                .createdAt(bankBox.getCreatedAt())
                .status(bankBox.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public BankBoxEntity update(BankBoxUpdateDTO bankBox, Long id) {
        Optional<BankBoxEntity> bankBoxFind = this.repository.findById(id);
        if (!bankBoxFind.isPresent()) {

        }
        BankBoxEntity update = BankBoxEntity.builder()
                .id(id)
                .store(bankBox.getStore())
                .name(bankBox.getName())
                .build();
        return this.repository.save(update);
    }

    @Override
    public PageDTO<BankBoxPaginationDTO> pagination(Pageable pageable) {
        Page<BankBoxEntity> bankBoxPage = this.repository.findAll(pageable);
        List<BankBoxPaginationDTO> dto = bankBoxPage.getContent()
                .stream()
                .map(bankBox -> new BankBoxPaginationDTO(bankBox))
                .toList();
        return new PageDTO<>(dto, bankBoxPage.getNumber(), bankBoxPage.getSize(), bankBoxPage.getTotalElements());
    }

}
