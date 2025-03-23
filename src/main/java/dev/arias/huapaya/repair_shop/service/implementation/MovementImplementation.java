package dev.arias.huapaya.repair_shop.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
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
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovementImplementation implements MovementService {

        private final MovementRepository repository;

        private final ProductService productService;

        private InboundEntity createOrUpdateInbound(InboundOutboundCreateDTO inout, MovementCreateDTO data) {
                InboundEntity inboundEntity;
                List<ProductStoreUpdateDTO> productStoreList = new ArrayList<>();
                Optional<ProductStoreEntity> productStoreOpt = productService
                                .findByIdAndProductStore_Store_Id(
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
                        inboundEntity = inbound;
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
                        inboundEntity = inbound;
                }
                return inboundEntity;
        }

        private OutboundEntity updateOutbound(InboundOutboundCreateDTO inout, MovementCreateDTO data) {
                OutboundEntity outboundEntity;
                List<ProductStoreUpdateDTO> productStoreList = new ArrayList<>();
                Optional<ProductStoreEntity> productStoreOpt = productService
                                .findByIdAndProductStore_Store_Id(
                                                inout.getProduct().getId(),
                                                data.getOriginStore().getId());
                if (productStoreOpt.isEmpty()) {
                        throw new ExceptionMessage("The exit movement cannot be performed");
                }

                if (inout.getQuantity() > productStoreOpt.get().getStock()) {
                        throw new ExceptionMessage("The quantity entered is greater than the product stock");
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
                outboundEntity = outbond;
                return outboundEntity;
        }

        private InboundEntity createInboundTransfer(InboundOutboundCreateDTO inout, MovementCreateDTO data) {
                InboundEntity inboundEntity;
                List<ProductStoreUpdateDTO> productStoreList = new ArrayList<>();
                Optional<ProductStoreEntity> productStoreOpt = productService
                                .findByIdAndProductStore_Store_Id(
                                                inout.getProduct().getId(),
                                                data.getDestinationStore().getId());
                if (productStoreOpt.isEmpty()) {
                        Optional<ProductEntity> productOpt = this.productService
                                        .findById(inout.getProduct().getId());
                        if (!productOpt.isPresent()) {
                                throw new ExceptionMessage("Not found product");
                        }
                        ProductEntity product = productOpt.get();
                        ProductStoreUpdateDTO productoStoreUpdate = ProductStoreUpdateDTO.builder()
                                        .store(data.getDestinationStore())
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
                        inboundEntity = inbound;
                } else {
                        Optional<ProductEntity> productOpt = this.productService
                                        .findById(inout.getProduct().getId());
                        if (!productOpt.isPresent()) {
                                throw new ExceptionMessage("Not found product");
                        }
                        ProductEntity product = productOpt.get();
                        Optional<Integer> stockProductStore = product.getProductStore().stream()
                                        .filter(ps -> ps.getStore().getId()
                                                        .equals(data.getDestinationStore().getId()))
                                        .map(ProductStoreEntity::getStock)
                                        .findFirst();
                        Optional<Long> IdProductStore = product.getProductStore().stream()
                                        .filter(ps -> ps.getStore().getId()
                                                        .equals(data.getDestinationStore().getId()))
                                        .map(ProductStoreEntity::getId)
                                        .findFirst();
                        ProductStoreUpdateDTO productoStoreUpdate = ProductStoreUpdateDTO.builder()
                                        .id(IdProductStore.get())
                                        .store(data.getDestinationStore())
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
                        inboundEntity = inbound;
                }
                return inboundEntity;
        }

        private Pair<InboundEntity, OutboundEntity> inventoryAdjustment(InboundOutboundCreateDTO inout,
                        MovementCreateDTO data) {
                InboundEntity inboundEntity = null;
                OutboundEntity outboundEntity = null;

                Optional<ProductEntity> productOpt = this.productService
                                .findById(inout.getProduct().getId());
                if (!productOpt.isPresent()) {
                        throw new ExceptionMessage("Not found product");
                }
                ProductEntity product = productOpt.get();

                Optional<ProductStoreEntity> productStoreOpt = product.getProductStore().stream()
                                .filter(p -> p.getStore().getId().equals(data.getOriginStore().getId()))
                                .findFirst();
                if (!productStoreOpt.isPresent()) {
                        throw new ExceptionMessage("Not found product store");
                }

                ProductStoreEntity productStore = productStoreOpt.get();

                if (productStore.getStock() > inout.getQuantity()) {
                        // !Outbound
                        inout.setQuantity(productStore.getStock() - inout.getQuantity());
                        outboundEntity = this.updateOutbound(inout, data);
                } else if (productStore.getStock() < inout.getQuantity()) {
                        // !Inbound
                        inout.setQuantity(inout.getQuantity() - productStore.getStock());
                        inboundEntity = this.createOrUpdateInbound(inout, data);
                } else {
                        // !Not action
                }

                return Pair.of(inboundEntity, outboundEntity);
        }

        @Override
        public MovementEntity create(MovementCreateDTO data) {
                List<InboundEntity> inboundList = new ArrayList<>();
                List<OutboundEntity> outboundList = new ArrayList<>();
                for (InboundOutboundCreateDTO inout : data.getInboundOutbound()) {
                        if (data.getReason().getValue().equals("I")) {
                                inboundList.add(this.createOrUpdateInbound(inout, data));
                        } else if (data.getReason().getValue().equals("O")) {
                                outboundList.add(this.updateOutbound(inout, data));
                                if (data.getReason().getDescription().contains("TRASLADO")) {
                                        inboundList.add(this.createInboundTransfer(inout, data));
                                }
                        } else if (data.getReason().getValue().equals("N")) {
                                Pair<InboundEntity, OutboundEntity> pairAdjustment = this
                                                .inventoryAdjustment(inout, data);
                                if (pairAdjustment.getLeft() != null) {
                                        inboundList.add(pairAdjustment.getLeft());
                                }
                                if (pairAdjustment.getRight() != null) {
                                        outboundList.add(pairAdjustment.getRight());
                                }
                        } else {
                                throw new ExceptionMessage("The movement cannot be performed");
                        }
                }
                if (inboundList.size() == 0 && outboundList.size() == 0) {
                        throw new ExceptionMessage("The movement cannot be performed");
                }

                MovementEntity movementCreate = MovementEntity.builder()
                                .supplier(data.getSupplier())
                                .reason(data.getReason())
                                .originStore(data.getOriginStore())
                                .destinationStore(data.getDestinationStore())
                                .referenceMovement(data.getReferenceMovement())
                                .sale(data.getSale())
                                .operationDate(data.getOperationDate())
                                .observation(data.getObservation())
                                .exchangeRate(data.getExchangeRate())
                                .tax(data.getTax())
                                .inbound(inboundList)
                                .outbound(outboundList)
                                .build();
                movementCreate = this.repository.save(movementCreate);
                if (data.getReason().getDescription().contains("TRASLADO")) {
                        MovementEntity movementDestination = MovementEntity.builder()
                                        .reason(data.getReason()).originStore(data.getDestinationStore())
                                        .destinationStore(null)
                                        .referenceMovement(movementCreate).sale(data.getSale())
                                        .operationDate(data.getOperationDate())
                                        .observation(data.getObservation())
                                        .exchangeRate(data.getExchangeRate())
                                        .inbound(inboundList)
                                        .outbound(outboundList)
                                        .build();
                        this.repository.save(movementDestination);
                }

                return movementCreate;
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
                                .exchangeRate(movement.getExchangeRate())
                                .tax(movement.getTax())
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
        public PageDTO<MovementPaginationDTO> pagination(Pageable pageable, String type) {
                if (type == null || type.isBlank()) {
                        throw new ExceptionMessage("The type sent is incorrect");
                }

                Set<String> validTypes = Set.of("I", "O");
                String upperType = type.toUpperCase();

                if (!validTypes.contains(upperType)) {
                        throw new ExceptionMessage("The type send is incorrect");
                }
                Page<MovementEntity> movementPage = this.repository.findByReasonValue(upperType, pageable);
                List<MovementPaginationDTO> dto = movementPage.getContent()
                                .stream()
                                .map(movement -> new MovementPaginationDTO(movement))
                                .toList();
                return new PageDTO<>(dto, movementPage.getNumber(), movementPage.getSize(),
                                movementPage.getTotalElements());
        }

}
