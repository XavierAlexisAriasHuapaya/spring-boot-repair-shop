package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.SaleRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.InboundOutboundCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SaleCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SaleFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.SalePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.details.SaleDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MovementService;
import dev.arias.huapaya.repair_shop.service.interfaces.SaleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SaleImplementation implements SaleService {

    private final SaleRepository repository;

    private final MovementService movementService;

    private final MasterDetailRepository masterDetailRepository;

    @Transactional
    @Override
    public SaleEntity create(SaleCreateDTO sale) {
        List<SaleDetailEntity> saleDetailEntities = new ArrayList<>();
        List<InboundOutboundCreateDTO> inboundList = new ArrayList<>();
        for (SaleDetailCreateDTO details : sale.getSaleDetails()) {
            SaleDetailEntity saleDetailCreate = SaleDetailEntity.builder()
                    .product(details.getProduct())
                    .quantity(details.getQuantity())
                    .price(details.getPrice())
                    .discount(details.getDiscount())
                    .porcentageDiscount(details.getPorcentageDiscount())
                    .build();
            saleDetailEntities.add(saleDetailCreate);

            InboundOutboundCreateDTO inbound = InboundOutboundCreateDTO.builder()
                    .product(details.getProduct())
                    .quantity(details.getQuantity())
                    .price(details.getPrice())
                    .build();
            inboundList.add(inbound);
        }
        SaleEntity saleCreate = SaleEntity.builder()
                .document(sale.getDocument())
                .paymentCondition(sale.getPaymentCondition())
                .pettyCash(sale.getPettyCash())
                .deliveryStatus(sale.getDeliveryStatus())
                .client(sale.getClient())
                .store(sale.getStore())
                .serie(sale.getSerie())
                .number(sale.getNumber())
                .operationDate(sale.getOperationDate())
                .deliveryDate(sale.getDeliveryDate())
                .expirationDate(sale.getExpirationDate())
                .saleDetails(saleDetailEntities)
                .taxAmount(sale.getTaxAmount())
                .observation(sale.getObservation())
                .build();
        saleCreate = this.repository.save(saleCreate);
        if (saleCreate.getId() == 0) {
            throw new ExceptionMessage("Error creating Sale");
        }
        Optional<MasterDetailEntity> reason = this.masterDetailRepository.findById(26L);
        MovementCreateDTO movementDto = MovementCreateDTO.builder()
                .reason(reason.get())
                .originStore(sale.getStore())
                .destinationStore(null)
                .referenceMovement(null)
                .sale(saleCreate)
                .operationDate(sale.getOperationDate())
                .observation(sale.getObservation())
                .taxAmount(sale.getTaxAmount())
                .inboundOutbound(inboundList)
                .build();
        MovementEntity movement = this.movementService.create(movementDto);
        if (movement.getId() == 0) {
            throw new ExceptionMessage("Error creating Sale Movement");
        }
        return saleCreate;
    }

    @Override
    public PageDTO<SalePaginationDTO> pagination(Pageable pageable) {
        Page<SaleEntity> salePage = this.repository.findAll(pageable);
        List<SalePaginationDTO> pagination = salePage.getContent().stream()
                .map(sale -> new SalePaginationDTO(sale))
                .toList();
        return new PageDTO<>(pagination, salePage.getNumber(), salePage.getSize(), salePage.getTotalElements());
    }

    @Override
    public Optional<SaleFindOneDTO> findOne(Long id) {
        Optional<SaleEntity> saleOpt = this.repository.findById(id);
        if (!saleOpt.isPresent()) {
            throw new ExceptionMessage("Not found sale id: " + id);
        }
        SaleEntity sale = saleOpt.get();
        SaleFindOneDTO dto = SaleFindOneDTO.builder()
                .id(id)
                .document(sale.getDocument())
                .paymentCondition(sale.getPaymentCondition())
                .pettyCash(sale.getPettyCash())
                .deliveryStatus(sale.getDeliveryStatus())
                .client(sale.getClient())
                .store(sale.getStore())
                .serie(sale.getSerie())
                .number(sale.getNumber())
                .operationDate(sale.getOperationDate())
                .deliveryDate(sale.getDeliveryDate())
                .expirationDate(sale.getExpirationDate())
                .saleDetails(sale.getSaleDetails())
                .observation(sale.getObservation())
                .saleAmount(sale.getSaleAmount())
                .taxAmount(sale.getTaxAmount())
                .subTotal(sale.getSubTotal())
                .discount(sale.getDiscount())
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .status(sale.getStatus())
                .build();
        return Optional.of(dto);
    }

}
