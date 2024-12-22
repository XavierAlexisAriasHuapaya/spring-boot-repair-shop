package dev.arias.huapaya.repair_shop.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;

public interface ProductStoreRepository extends JpaRepository<ProductStoreEntity, Long> {

    public Optional<ProductStoreEntity> findByProduct_IdAndStore_Id(Long productId, Long storeId);

}
