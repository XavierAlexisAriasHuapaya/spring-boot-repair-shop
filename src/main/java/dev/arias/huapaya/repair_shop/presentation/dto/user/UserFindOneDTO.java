package dev.arias.huapaya.repair_shop.presentation.dto.user;

import java.time.LocalDateTime;
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
public class UserFindOneDTO {

    private Long id;

    private String username;

    private String email;

    private RolEntity rol;

    private List<UserStoreEntity> userStore;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

}
