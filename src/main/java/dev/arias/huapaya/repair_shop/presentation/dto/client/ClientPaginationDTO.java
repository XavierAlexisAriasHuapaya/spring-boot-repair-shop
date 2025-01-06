package dev.arias.huapaya.repair_shop.presentation.dto.client;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientPaginationDTO {

    private Long id;

    private MasterDetailEntity typePerson;

    private String identityNumber;

    private String fullName;

    private String phone;

    private String email;

    private boolean status;

    public ClientPaginationDTO(ClientEntity client) {
        this.id = client.getId();
        this.typePerson = client.getTypePerson();
        this.identityNumber = client.getIdentityNumber();
        this.fullName = client.getFirstName() + " " + client.getLastName();
        this.phone = client.getPhone();
        this.email = client.getEmail();
        this.status = client.getStatus();
    }

}
