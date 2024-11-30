package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;

public interface PurchaseBillRepository extends JpaRepository<PurchaseBillEntity, Long> {

}
