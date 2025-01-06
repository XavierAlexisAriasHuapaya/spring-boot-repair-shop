package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;

@Repository
public interface MasterDetailRepository extends JpaRepository<MasterDetailEntity, Long> {


}
