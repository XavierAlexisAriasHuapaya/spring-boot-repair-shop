package dev.arias.huapaya.repair_shop.presentation.dto.employee;

import dev.arias.huapaya.repair_shop.presentation.dto.user.UserCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCreateDTO {

    private UserCreateDTO user;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

}
