package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}
