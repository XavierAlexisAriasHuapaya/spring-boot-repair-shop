package dev.arias.huapaya.repair_shop.presentation.dto.employee;

import dev.arias.huapaya.repair_shop.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeePaginationDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private String address;

    private String phone;

    private boolean status;

    public EmployeePaginationDTO(EmployeeEntity employee) {
        this.id = employee.getId();
        this.identityNumber = employee.getIdentityNumber();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.status = employee.isStatus();
    }

}
