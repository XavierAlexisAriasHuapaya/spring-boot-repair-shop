package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;

public interface SaleBillRepository extends JpaRepository<SaleBillEntity, Long> {

}
