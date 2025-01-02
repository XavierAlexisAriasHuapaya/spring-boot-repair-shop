package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.ProductRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductMovementUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product_store.ProductStoreUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.ProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductImplementation implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductEntity create(ProductCreateDTO data) {
        ProductEntity productCreate = ProductEntity.builder()
                .unitOfMeasure(data.getUnitOfMeasure())
                .category(data.getCategory())
                .model(data.getModel())
                .brand(data.getBrand())
                .name(data.getName())
                .unitPrice(data.getUnitPrice())
                .purchasePrice(data.getPurchasePrice())
                .minimumStock(data.getMinimumStock())
                .image(data.getImage())
                .build();
        return this.repository.save(productCreate);
    }

    @Override
    public ProductEntity update(ProductUpdateDTO data, Long id) {
        Optional<ProductEntity> productFind = this.repository.findById(id);
        if (!productFind.isPresent()) {

        }
        ProductEntity product = productFind.get();
        product.setUnitOfMeasure(data.getUnitOfMeasure());
        product.setCategory(data.getCategory());
        product.setModel(data.getModel());
        product.setBrand(data.getBrand());
        product.setName(data.getName());
        product.setUnitPrice(data.getUnitPrice());
        product.setPurchasePrice(data.getPurchasePrice());
        product.setMinimumStock(data.getMinimumStock());
        product.setImage(data.getImage());
        return this.repository.save(product);
    }

    @Override
    public Optional<ProductFindOneDTO> findOne(Long id) {
        Optional<ProductEntity> productFind = this.repository.findById(id);
        if (!productFind.isPresent()) {
            return Optional.empty();
        }
        ProductEntity product = productFind.get();
        ProductFindOneDTO dto = ProductFindOneDTO.builder()
                .id(id)
                .unitOfMeasure(product.getUnitOfMeasure())
                .category(product.getCategory())
                .model(product.getModel())
                .brand(product.getBrand())
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .purchasePrice(product.getPurchasePrice())
                .minimumStock(product.getMinimumStock())
                .image(product.getImage())
                .productStore(product.getProductStore())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .status(product.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<ProductPaginationDTO> pagination(Pageable pageable) {
        Page<ProductEntity> productPage = this.repository.findAll(pageable);
        List<ProductPaginationDTO> productList = productPage.getContent()
                .stream()
                .map(product -> new ProductPaginationDTO(product))
                .toList();
        return new PageDTO<>(productList, productPage.getNumber(), productPage.getSize(),
                productPage.getTotalElements());
    }

    @Override
    public ProductEntity updateProductMovement(ProductMovementUpdateDTO data, Long id) {
        Optional<ProductEntity> productFind = this.repository.findById(id);
        if (!productFind.isPresent()) {
            throw new ExceptionMessage("Not found product");
        }
        ProductEntity product = productFind.get();
        for (ProductStoreUpdateDTO productMovement : data.getProductStore()) {

            ProductStoreEntity productStore = product.getProductStore().stream()
                    .filter(p -> p.getId().equals(productMovement.getId()))
                    .findFirst()
                    .orElse(null);

            if (productStore != null) {
                productStore.setStore(productMovement.getStore());
                productStore.setStock(productMovement.getStock());
                productStore.setSalePrice(productMovement.getSalePrice());
                productStore.setPurchasePrice(productMovement.getPurchasePrice());
            } else {
                productStore = ProductStoreEntity.builder()
                        .id(productMovement.getId())
                        .store(productMovement.getStore())
                        .stock(productMovement.getStock())
                        .salePrice(productMovement.getSalePrice())
                        .purchasePrice(productMovement.getPurchasePrice())
                        .build();
                product.getProductStore().add(productStore);
            }
            // updatedProductStoreList.add(productStore);
        }
        // product.setProductStore(updatedProductStoreList);
        return this.repository.save(product);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        Optional<ProductEntity> productFind = this.repository.findById(id);
        if (!productFind.isPresent()) {
            return Optional.empty();
        }
        return productFind;
    }

    @Override
    public Optional<ProductStoreEntity> findByIdAndProductStore_Store_Id(Long productId, Long storeId) {
        Optional<ProductEntity> productOpt = this.repository.findByIdAndProductStore_Store_Id(productId,
                storeId);
        if (!productOpt.isPresent()) {
            return Optional.empty();
        }
        ProductEntity product = productOpt.get();
        ProductStoreEntity productStoreOpt = product.getProductStore().get(0);
        if (productStoreOpt == null) {
            return Optional.empty();
        }
        return Optional.of(productStoreOpt);
    }

}
