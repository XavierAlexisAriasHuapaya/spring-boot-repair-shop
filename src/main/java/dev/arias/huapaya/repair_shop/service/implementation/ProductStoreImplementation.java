package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.ProductStoreRepository;
import dev.arias.huapaya.repair_shop.service.interfaces.ProductStoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductStoreImplementation implements ProductStoreService {

    private final ProductStoreRepository repository;

    @Override
    public Optional<ProductStoreEntity> findByProduct_IdAndStore_Id(Long productId, Long storeId) {
        Optional<ProductStoreEntity> productStoreOpt = this.repository.findByProduct_IdAndStore_Id(productId, storeId);
        if (!productStoreOpt.isPresent()) {
            return Optional.empty();
        }
        return productStoreOpt;
    }

}
