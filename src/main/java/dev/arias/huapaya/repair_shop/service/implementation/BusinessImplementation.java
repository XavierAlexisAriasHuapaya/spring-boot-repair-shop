package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.repair_shop.persistence.entity.BusinessEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.BusinessRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.BusinessService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BusinessImplementation implements BusinessService {

    private final BusinessRepository repository;

    @Transactional(readOnly = false)
    @Override
    public BusinessEntity create(BusinessCreateDTO data) {
        BusinessEntity business = BusinessEntity.builder()
                .country(data.getCountry())
                .companyName(data.getCompanyName())
                .phone(data.getPhone())
                .address(data.getAddress())
                .build();
        return this.repository.save(business);
    }

    @Transactional(readOnly = false)
    @Override
    public BusinessEntity update(BusinessUpdateDTO data, Long id) {
        BusinessEntity findUpdate = this.repository.findById(id).get();
        findUpdate.setCountry(data.getCountry());
        findUpdate.setCompanyName(data.getCompanyName());
        findUpdate.setPhone(data.getPhone());
        findUpdate.setAddress(data.getAddress());
        return this.repository.save(findUpdate);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BusinessFindOneDTO> findOne(Long id) {
        Optional<BusinessEntity> businessOpt = this.repository.findById(id);
        if (!businessOpt.isPresent()) {
            return Optional.empty();
        }
        BusinessEntity business = businessOpt.get();
        BusinessFindOneDTO dto = BusinessFindOneDTO.builder()
                .id(id)
                .country(business.getCountry())
                .companyName(business.getCompanyName())
                .phone(business.getPhone())
                .address(business.getAddress())
                .build();
        return Optional.of(dto);
    }

}
