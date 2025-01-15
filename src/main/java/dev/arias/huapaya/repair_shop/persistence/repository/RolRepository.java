package dev.arias.huapaya.repair_shop.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    public Optional<RolEntity> findByDescription(String description);

}
