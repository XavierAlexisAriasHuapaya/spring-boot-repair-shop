package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.SupplierRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.supplier.SupplierUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.SupplierService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SupplierImplementation implements SupplierService {

    private final SupplierRepository repository;

    @Override
    public SupplierEntity create(SupplierCreateDTO data) {
        SupplierEntity supplierCreate = SupplierEntity.builder()
                .typeSupplier(data.getTypeSupplier())
                .country(data.getCountry())
                .companyName(data.getCompanyName())
                .identityNumber(data.getIdentityNumber())
                .address(data.getAddress())
                .phone(data.getPhone())
                .web(data.getWeb())
                .build();
        return this.repository.save(supplierCreate);
    }

    @Override
    public SupplierEntity update(SupplierUpdateDTO data, Long id) {
        Optional<SupplierEntity> supplierFindOne = this.repository.findById(id);
        if (!supplierFindOne.isPresent()) {

        }
        SupplierEntity supplierUpdate = SupplierEntity.builder()
                .id(id)
                .typeSupplier(data.getTypeSupplier())
                .country(data.getCountry())
                .companyName(data.getCompanyName())
                .identityNumber(data.getIdentityNumber())
                .address(data.getAddress())
                .phone(data.getPhone())
                .web(data.getWeb())
                .build();
        return this.repository.save(supplierUpdate);
    }

    @Override
    public Optional<SupplierFindOneDTO> findOne(Long id) {
        Optional<SupplierEntity> supplierFindOne = this.repository.findById(id);
        if (!supplierFindOne.isPresent()) {
            return Optional.empty();
        }
        SupplierEntity supplier = supplierFindOne.get();
        SupplierFindOneDTO dto = SupplierFindOneDTO.builder()
                .id(id)
                .typeSupplier(supplier.getTypeSupplier())
                .country(supplier.getCountry())
                .companyName(supplier.getCompanyName())
                .identityNumber(supplier.getIdentityNumber())
                .address(supplier.getAddress())
                .phone(supplier.getPhone())
                .web(supplier.getWeb())
                .createdAt(supplier.getCreatedAt())
                .status(supplier.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<SupplierPaginationDTO> pagination(Pageable pageable) {
        Page<SupplierEntity> supplierPage = this.repository.findAll(pageable);
        List<SupplierPaginationDTO> supplierList = supplierPage.getContent()
                .stream()
                .map(supplier -> new SupplierPaginationDTO(supplier))
                .toList();
        return new PageDTO<>(supplierList, supplierPage.getNumber(),
                supplierPage.getSize(), supplierPage.getTotalElements());
    }

}
