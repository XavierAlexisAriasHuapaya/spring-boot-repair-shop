package dev.arias.huapaya.repair_shop.presentation.dto.user;

import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.UserStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateDTO {

    private String username;

    private String password;

    private String email;

    // private String name;

    // private String lastName;

    private RolEntity rol;

    private List<UserStoreEntity> userStore;

}
