package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductUpdateDTO;

public interface ProductService {

    public ProductEntity create(ProductCreateDTO data);

    public ProductEntity update(ProductUpdateDTO data, Long id);

    public Optional<ProductFindOneDTO> findOne(Long id);

    public PageDTO<ProductPaginationDTO> pagination(Pageable pageable);

}