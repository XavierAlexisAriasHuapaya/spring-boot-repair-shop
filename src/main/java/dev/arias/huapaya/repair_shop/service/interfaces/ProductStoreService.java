package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;

public interface ProductStoreService {

    public Optional<ProductStoreEntity> findByProduct_IdAndStore_Id(Long productId, Long storeId);

}
