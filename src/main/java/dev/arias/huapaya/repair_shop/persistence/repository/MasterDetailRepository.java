package dev.arias.huapaya.repair_shop.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;

@Repository
public interface MasterDetailRepository extends JpaRepository<MasterDetailEntity, Long> {

    public Optional<MasterDetailEntity> findByDescription(String description);

    public Page<MasterDetailEntity> findByMasterId(Long id, Pageable pageable);

}
