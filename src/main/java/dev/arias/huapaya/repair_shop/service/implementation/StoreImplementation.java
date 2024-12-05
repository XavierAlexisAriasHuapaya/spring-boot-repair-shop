package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.StoreRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StorePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.StoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StoreImplementation implements StoreService {

    private final StoreRepository repository;

    @Override
    public StoreEntity create(StoreCreateDTO data) {
        StoreEntity storeCreate = StoreEntity.builder()
                .currency(data.getCurrency())
                .name(data.getName())
                .address(data.getAddress())
                .phone(data.getPhone())
                .logo(data.getLogo())
                .build();
        return this.repository.save(storeCreate);
    }

    @Override
    public StoreEntity update(StoreUpdateDTO data, Long id) {
        StoreEntity findUpdate = this.repository.findById(id).get();
        findUpdate.setCurrency(data.getCurrency());
        findUpdate.setName(data.getName());
        findUpdate.setAddress(data.getAddress());
        findUpdate.setPhone(data.getPhone());
        findUpdate.setLogo(data.getLogo());
        return this.repository.save(findUpdate);
    }

    @Override
    public Optional<StoreFindOneDTO> findOne(Long id) {
        Optional<StoreEntity> storeFind = this.repository.findById(id);
        if (!storeFind.isPresent()) {
            return Optional.empty();
        }
        StoreEntity store = storeFind.get();
        StoreFindOneDTO dto = StoreFindOneDTO.builder()
                .id(id)
                .currency(store.getCurrency())
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .logo(store.getLogo())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<StorePaginationDTO> pagination(Pageable pageable) {
        Page<StoreEntity> storePage = this.repository.findAll(pageable);
        List<StorePaginationDTO> storeDTO = storePage.getContent().stream()
                .map(store -> new StorePaginationDTO(store))
                .toList();
        return new PageDTO<>(storeDTO, storePage.getNumber(), storePage.getSize(), storePage.getTotalElements());
    }

}
