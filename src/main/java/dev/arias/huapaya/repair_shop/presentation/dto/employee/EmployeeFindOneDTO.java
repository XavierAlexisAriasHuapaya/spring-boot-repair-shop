package dev.arias.huapaya.repair_shop.presentation.dto.employee;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.presentation.dto.user.UserFindOneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeFindOneDTO {

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

}
