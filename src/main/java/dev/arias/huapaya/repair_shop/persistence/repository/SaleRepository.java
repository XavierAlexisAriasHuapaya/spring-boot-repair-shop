package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

}
