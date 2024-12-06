package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.EmployeeEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.UserEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.EmployeeRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StorePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.user.UserFindOneDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.EmployeeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeImplementation implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public EmployeeEntity create(EmployeeCreateDTO data) {
        UserEntity userCreate = UserEntity.builder()
                .username(data.getUser().getUsername())
                .password(data.getUser().getPassword())
                .email(data.getUser().getEmail())
                .rol(data.getUser().getRol())
                .build();
        EmployeeEntity employeeCreate = EmployeeEntity.builder()
                .user(userCreate)
                .identityNumber(data.getIdentityNumber())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .address(data.getAddress())
                .phone(data.getPhone())
                .build();
        return this.repository.save(employeeCreate);
    }

    @Override
    public EmployeeEntity update(EmployeeUpdateDTO data, Long id) {
        EmployeeEntity findUpdate = this.repository.findById(id).get();
        UserEntity userUpdate = UserEntity.builder()
                .id(findUpdate.getUser().getId())
                .username(data.getUser().getUsername())
                .password(data.getUser().getPassword())
                .email(data.getUser().getEmail())
                .rol(data.getUser().getRol())
                .build();
        findUpdate.setUser(userUpdate);
        findUpdate.setIdentityNumber(data.getIdentityNumber());
        findUpdate.setFirstName(data.getFirstName());
        findUpdate.setLastName(data.getLastName());
        findUpdate.setAddress(data.getAddress());
        findUpdate.setPhone(data.getPhone());
        return this.repository.save(findUpdate);
    }

    @Override
    public Optional<EmployeeFindOneDTO> findOne(Long id) {
        Optional<EmployeeEntity> employeeOpt = this.repository.findById(id);
        if (!employeeOpt.isPresent()) {
            return Optional.empty();
        }
        EmployeeEntity employee = employeeOpt.get();
        UserFindOneDTO userDto = UserFindOneDTO.builder()
                .id(employee.getUser().getId())
                .username(employee.getUser().getUsername())
                .email(employee.getUser().getEmail())
                .rol(employee.getUser().getRol())
                .userStore(employee.getUser().getUserStore())
                .createdAt(employee.getUser().getCreatedAt())
                .updatedAt(employee.getUser().getUpdatedAt())
                .status(employee.getUser().getStatus())
                .build();
        EmployeeFindOneDTO dto = EmployeeFindOneDTO.builder()
                .id(id)
                .user(userDto)
                .identityNumber(employee.getIdentityNumber())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .status(employee.isStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<EmployeePaginationDTO> pagination(Pageable pageable) {
        Page<EmployeeEntity> employeePage = this.repository.findAll(pageable);
        List<EmployeePaginationDTO> employeeDTO = employeePage.getContent().stream()
                .map(employee -> new EmployeePaginationDTO(employee))
                .toList();
        return new PageDTO<>(employeeDTO, employeePage.getNumber(), employeePage.getSize(),
                employeePage.getTotalElements());
    }

}
