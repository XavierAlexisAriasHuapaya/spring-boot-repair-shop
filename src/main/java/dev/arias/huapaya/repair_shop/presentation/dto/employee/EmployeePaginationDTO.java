package dev.arias.huapaya.repair_shop.presentation.dto.employee;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.EmployeeEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.user.UserFindOneDTO;
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

    private UserFindOneDTO user;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean status;

    public EmployeePaginationDTO(EmployeeEntity employee) {
        UserFindOneDTO user = UserFindOneDTO.builder()
                .id(employee.getUser().getId())
                .username(employee.getUser().getUsername())
                .email(employee.getUser().getEmail())
                .rol(employee.getUser().getRol())
                .userStore(employee.getUser().getUserStore())
                .createdAt(employee.getUser().getCreatedAt())
                .updatedAt(employee.getUser().getUpdatedAt())
                .status(employee.getUser().getStatus())
                .build();
        this.id = employee.getId();
        this.user = user;
        this.identityNumber = employee.getIdentityNumber();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.createdAt = employee.getCreatedAt();
        this.updatedAt = employee.getUpdatedAt();
        this.status = employee.isStatus();
    }

}
