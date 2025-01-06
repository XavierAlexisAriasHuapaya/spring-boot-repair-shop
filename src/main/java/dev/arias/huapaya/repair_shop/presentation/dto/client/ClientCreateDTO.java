package dev.arias.huapaya.repair_shop.presentation.dto.client;

import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientCreateDTO {

    private MasterDetailEntity typePerson;

    private MasterDetailEntity gender;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private LocalDate brithOrAnniversary;

    private String address;

    private String phone;

    private String email;

    private String occupation;

    private String observation;

}
