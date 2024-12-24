package dev.arias.huapaya.repair_shop.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    public Optional<ProductEntity> findByIdAndProductStore_Store_Id(Long productId, Long storeId);

}
