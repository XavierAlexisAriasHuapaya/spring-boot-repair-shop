package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.EmployeeEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.employee.EmployeeUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;

public interface EmployeeService {

    public EmployeeEntity create(EmployeeCreateDTO data);

    public EmployeeEntity update(EmployeeUpdateDTO data, Long id);

    public Optional<EmployeeFindOneDTO> findOne(Long id);

    public PageDTO<EmployeePaginationDTO> pagination(Pageable pageable);

}
