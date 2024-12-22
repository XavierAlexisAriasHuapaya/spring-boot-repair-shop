package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

}
