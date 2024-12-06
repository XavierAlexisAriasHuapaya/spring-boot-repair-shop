package dev.arias.huapaya.repair_shop.presentation.dto.employee;

import dev.arias.huapaya.repair_shop.presentation.dto.user.UserUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeUpdateDTO {

    private UserUpdateDTO user;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

}
