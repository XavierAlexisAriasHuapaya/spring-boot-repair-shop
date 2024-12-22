package dev.arias.huapaya.repair_shop.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.InboundEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.OutboundEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.ProductEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.ProductStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MovementRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.InboundOutboundCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductMovementUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product_store.ProductStoreUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MovementService;
import dev.arias.huapaya.repair_shop.service.interfaces.ProductService;
import dev.arias.huapaya.repair_shop.service.interfaces.ProductStoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovementImplementation implements MovementService {

        private final MovementRepository repository;

        private final ProductService productService;

        private final ProductStoreService productStoreService;

        private List<InboundEntity> createOrUpdateInbound(InboundOutboundCreateDTO inout, MovementCreateDTO data) {
                List<InboundEntity> inboundList = new ArrayList<>();
                List<ProductStoreUpdateDTO> productStoreList = new ArrayList<>();
                Optional<ProductStoreEntity> productStoreOpt = productStoreService
                                .findByProduct_IdAndStore_Id(
                                                inout.getProduct().getId(),
                                                data.getOriginStore().getId());
                if (productStoreOpt.isEmpty()) {
                        Optional<ProductEntity> productOpt = this.productService
                                        .findById(inout.getProduct().getId());
                        if (!productOpt.isPresent()) {
                                throw new ExceptionMessage("Not found product");
                        }
                        ProductEntity product = productOpt.get();
                        ProductStoreUpdateDTO productoStoreUpdate = ProductStoreUpdateDTO.builder()
                                        .store(data.getOriginStore())
                                        .stock(inout.getQuantity())
                                        .salePrice(BigDecimal.ZERO)
                                        .purchasePrice(BigDecimal.ZERO)
                                        .build();
                        productStoreList.add(productoStoreUpdate);
                        ProductMovementUpdateDTO productStore = ProductMovementUpdateDTO.builder()
                                        .productStore(productStoreList)
                                        .build();
                        this.productService.updateProductMovement(productStore, product.getId());
                        InboundEntity inbound = InboundEntity.builder()
                                        .product(inout.getProduct())
                                        .quantity(inout.getQuantity())
                                        .salePrice(inout.getPrice())
                                        .build();
                        inboundList.add(inbound);
                        productStoreList.clear();
                } else {
                        Optional<ProductEntity> productOpt = this.productService
                                        .findById(inout.getProduct().getId());
                        if (!productOpt.isPresent()) {
                                throw new ExceptionMessage("Not found product");
                        }
                        ProductEntity product = productOpt.get();
                        Optional<Integer> stockProductStore = product.getProductStore().stream()
                                        .filter(ps -> ps.getStore().getId()
                                                        .equals(data.getOriginStore().getId()))
                                        .map(ProductStoreEntity::getStock)
                                        .findFirst();
                        Optional<Long> IdProductStore = product.getProductStore().stream()
                                        .filter(ps -> ps.getStore().getId()
                                                        .equals(data.getOriginStore().getId()))
                                        .map(ProductStoreEntity::getId)
                                        .findFirst();
                        ProductStoreUpdateDTO productoStoreUpdate = ProductStoreUpdateDTO.builder()
                                        .id(IdProductStore.get())
                                        .store(data.getOriginStore())
                                        .stock(inout.getQuantity() + stockProductStore.get())
                                        .salePrice(BigDecimal.ZERO)
                                        .purchasePrice(BigDecimal.ZERO)
                                        .build();
                        productStoreList.add(productoStoreUpdate);
                        ProductMovementUpdateDTO productStore = ProductMovementUpdateDTO.builder()
                                        .productStore(productStoreList)
                                        .build();
                        this.productService.updateProductMovement(productStore, product.getId());
                        InboundEntity inbound = InboundEntity.builder()
                                        .product(inout.getProduct())
                                        .quantity(inout.getQuantity())
                                        .salePrice(inout.getPrice())
                                        .build();
                        inboundList.add(inbound);
                        productStoreList.clear();
                }
                return inboundList;
        }

        private List<OutboundEntity> updateOutbound(InboundOutboundCreateDTO inout, MovementCreateDTO data) {
                List<OutboundEntity> outboundList = new ArrayList<>();
                List<ProductStoreUpdateDTO> productStoreList = new ArrayList<>();
                Optional<ProductStoreEntity> productStoreOpt = productStoreService
                                .findByProduct_IdAndStore_Id(
                                                inout.getProduct().getId(),
                                                data.getOriginStore().getId());
                if (productStoreOpt.isEmpty()) {
                        throw new ExceptionMessage("The exit movement cannot be performed");
                }

                Optional<ProductEntity> productOpt = this.productService
                                .findById(inout.getProduct().getId());
                if (!productOpt.isPresent()) {
                        throw new ExceptionMessage("Not found product");
                }
                ProductEntity product = productOpt.get();
                Optional<Integer> stockProductStore = product.getProductStore().stream()
                                .filter(ps -> ps.getStore().getId()
                                                .equals(data.getOriginStore().getId()))
                                .map(ProductStoreEntity::getStock)
                                .findFirst();
                Optional<Long> IdProductStore = product.getProductStore().stream()
                                .filter(ps -> ps.getStore().getId()
                                                .equals(data.getOriginStore().getId()))
                                .map(ProductStoreEntity::getId)
                                .findFirst();
                ProductStoreUpdateDTO productoStoreUpdate = ProductStoreUpdateDTO.builder()
                                .id(IdProductStore.get())
                                .store(data.getOriginStore())
                                .stock(stockProductStore.get() - inout.getQuantity())
                                .salePrice(BigDecimal.ZERO)
                                .purchasePrice(BigDecimal.ZERO)
                                .build();
                productStoreList.add(productoStoreUpdate);
                ProductMovementUpdateDTO productStore = ProductMovementUpdateDTO.builder()
                                .productStore(productStoreList)
                                .build();
                this.productService.updateProductMovement(productStore, product.getId());
                OutboundEntity outbond = OutboundEntity.builder()
                                .product(inout.getProduct())
                                .quantity(inout.getQuantity())
                                .purchasePrice(inout.getPrice())
                                .build();
                outboundList.add(outbond);
                productStoreList.clear();
                return outboundList;
        }

        @Override
        public MovementEntity create(MovementCreateDTO data) {
                List<InboundEntity> inboundList = new ArrayList<>();
                List<OutboundEntity> outboundList = new ArrayList<>();
                for (InboundOutboundCreateDTO inout : data.getInboundOutbound()) {
                        if (data.getReason().getValue().equals("I")) {
                                inboundList = this.createOrUpdateInbound(inout, data);
                        } else {
                                outboundList = this.updateOutbound(inout, data);
                        }
                }
                if (inboundList.size() == 0 && outboundList.size() == 0) {
                        throw new ExceptionMessage("The movement cannot be performed");
                }
                MovementEntity movementCreate = MovementEntity.builder()
                                .reason(data.getReason())
                                .originStore(data.getOriginStore())
                                .destinationStore(data.getDestinationStore())
                                .referenceMovement(data.getReferenceMovement())
                                .sale(data.getSale())
                                .operationDate(data.getOperationDate())
                                .observation(data.getObservation())
                                .taxAmount(data.getTaxAmount())
                                .inbound(inboundList)
                                .outbound(outboundList)
                                .build();
                return this.repository.save(movementCreate);
        }

        @Override
        public Optional<MovementFindOneDTO> findOne(Long id) {
                Optional<MovementEntity> movementFindOne = this.repository.findById(id);
                if (!movementFindOne.isPresent()) {
                        return Optional.empty();
                }
                MovementEntity movement = movementFindOne.get();
                MovementFindOneDTO dto = MovementFindOneDTO.builder()
                                .id(id)
                                .reason(movement.getReason())
                                .originStore(movement.getOriginStore())
                                .destinationStore(movement.getDestinationStore())
                                .referenceMovement(movement.getReferenceMovement())
                                .sale(movement.getSale())
                                .operationDate(movement.getOperationDate())
                                .observation(movement.getObservation())
                                .subTotal(movement.getSubTotal())
                                .taxAmount(movement.getTaxAmount())
                                .movementTotal(movement.getMovementTotal())
                                .inbound(movement.getInbound())
                                .outbound(movement.getOutbound())
                                .createdAt(movement.getCreatedAt())
                                .status(movement.getStatus())
                                .build();
                return Optional.of(dto);
        }

        @Override
        public PageDTO<MovementPaginationDTO> pagination(Pageable pageable) {
                Page<MovementEntity> movementPage = this.repository.findAll(pageable);
                List<MovementPaginationDTO> dto = movementPage.getContent()
                                .stream()
                                .map(movement -> new MovementPaginationDTO(movement))
                                .toList();
                return new PageDTO<>(dto, movementPage.getNumber(), movementPage.getSize(),
                                movementPage.getTotalElements());
        }

}
