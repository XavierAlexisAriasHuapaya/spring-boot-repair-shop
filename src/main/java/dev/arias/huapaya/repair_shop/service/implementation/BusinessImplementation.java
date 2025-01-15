package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.repair_shop.persistence.entity.BusinessEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.EmployeeEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.UserStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.BusinessRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.RolRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.user.UserCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.BusinessService;
import dev.arias.huapaya.repair_shop.service.interfaces.EmployeeService;
import dev.arias.huapaya.repair_shop.service.interfaces.StoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BusinessImplementation implements BusinessService {

    private final BusinessRepository repository;

    private final StoreService storeService;

    private final MasterDetailRepository masterDetailRepository;

    private final EmployeeService employeeService;

    private final RolRepository rolRepository;

    @Transactional(readOnly = false)
    @Override
    public BusinessEntity create(BusinessCreateDTO data) {

        Optional<MasterDetailEntity> mdCurrency = this.masterDetailRepository.findByDescription("SOLES");
        if (!mdCurrency.isPresent()) {
            throw new ExceptionMessage("not found currency");
        }
        StoreCreateDTO storeDto = StoreCreateDTO.builder()
                .name(data.getCompanyName())
                .currency(mdCurrency.get())
                .address(data.getAddress())
                .phone(data.getPhone())
                .build();

        StoreEntity store = this.storeService.create(storeDto);

        if (store.getId() == 0 || store == null) {
            throw new ExceptionMessage("Error when trying to register the store");
        }
        UserStoreEntity userStore = UserStoreEntity.builder()
                .store(store)
                .build();

        List<UserStoreEntity> userStoreList = new ArrayList<>();
        userStoreList.add(userStore);

        Optional<RolEntity> rolOpt = this.rolRepository.findByDescription("ADMINISTRATOR");
        if (!rolOpt.isPresent()) {
            throw new ExceptionMessage("not found rol");
        }

        UserCreateDTO userDto = UserCreateDTO.builder()
                .username("administrator")
                .password(data.getIdentityNumber())
                .rol(rolOpt.get())
                .userStore(userStoreList)
                .build();

        EmployeeCreateDTO employeeDto = EmployeeCreateDTO.builder()
                .user(userDto)
                .firstName("administrator")
                .address(data.getAddress())
                .phone(data.getPhone())
                .build();

        EmployeeEntity employee = this.employeeService.create(employeeDto);

        if (employee.getId() == 0 || employee == null) {
            throw new ExceptionMessage("Error when trying to register the employee");
        }

        BusinessEntity business = BusinessEntity.builder()
                .country(data.getCountry())
                .companyName(data.getCompanyName())
                .identityNumber(data.getIdentityNumber())
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
        findUpdate.setIdentityNumber(data.getIdentityNumber());
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
                .identityNumber(business.getIdentityNumber())
                .phone(business.getPhone())
                .address(business.getAddress())
                .build();
        return Optional.of(dto);
    }

}
