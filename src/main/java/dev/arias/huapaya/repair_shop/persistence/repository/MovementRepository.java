package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    Page<MovementEntity> findByReasonValue(String type, Pageable pageable);

}
