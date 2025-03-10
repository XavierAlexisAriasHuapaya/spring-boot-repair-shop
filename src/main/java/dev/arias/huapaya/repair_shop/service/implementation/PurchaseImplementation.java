package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.PurchaseRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.InboundOutboundCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchaseCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchaseFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurchasePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.details.PurchaseDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MovementService;
import dev.arias.huapaya.repair_shop.service.interfaces.PurchaseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseImplementation implements PurchaseService {

    private PurchaseRepository repository;

    private final MovementService movementService;

    private final MasterDetailRepository masterDetailRepository;

    @Override
    public PurchaseEntity create(PurchaseCreateDTO purchase) {

        List<PurchaseDetailEntity> purchaseDetailEntities = new ArrayList<>();
        List<InboundOutboundCreateDTO> outboundList = new ArrayList<>();
        for (PurchaseDetailCreateDTO details : purchase.getPurchaseDetails()) {
            PurchaseDetailEntity purchaseDetailsCreate = PurchaseDetailEntity.builder()
                    .product(details.getProduct())
                    .quantity(details.getQuantity())
                    .price(details.getPrice())
                    .build();
            purchaseDetailEntities.add(purchaseDetailsCreate);

            InboundOutboundCreateDTO inbound = InboundOutboundCreateDTO.builder()
                    .product(details.getProduct())
                    .quantity(details.getQuantity())
                    .price(details.getPrice())
                    .build();
            outboundList.add(inbound);
        }

        PurchaseEntity purchaseCreate = PurchaseEntity.builder()
                .supplier(purchase.getSupplier())
                .orderStatus(purchase.getOrderStatus())
                .store(purchase.getStore())
                .observation(purchase.getObservation())
                .operationDate(purchase.getOperationDate())
                .purchaseDetails(purchaseDetailEntities)
                .exchangeRate(purchase.getExchangeRate())
                .tax(purchase.getTax())
                .build();
        purchaseCreate = this.repository.save(purchaseCreate);

        if (purchaseCreate.getId() == 0) {
            throw new ExceptionMessage("Error creating Purchase");
        }

        Optional<MasterDetailEntity> reason = this.masterDetailRepository.findByDescription("COMPRA DIRECTA");
        MovementCreateDTO movementDto = MovementCreateDTO.builder()
                .supplier(purchase.getSupplier())
                .reason(reason.get())
                .originStore(purchase.getStore())
                .destinationStore(null)
                .referenceMovement(null)
                .sale(null)
                .operationDate(purchase.getOperationDate())
                .observation(purchase.getObservation())
                .exchangeRate(purchase.getExchangeRate())
                .tax(purchase.getTax())
                .inboundOutbound(outboundList)
                .build();
        MovementEntity movement = this.movementService.create(movementDto);
        if (movement.getId() == 0) {
            throw new ExceptionMessage("Error creating Purchase Movement");
        }

        return purchaseCreate;
    }

    @Override
    public PageDTO<PurchasePaginationDTO> pagination(Pageable pageable) {
        Page<PurchaseEntity> purchasePage = this.repository.findAll(pageable);
        List<PurchasePaginationDTO> purchaseDTO = purchasePage.getContent().stream()
                .map(purchase -> new PurchasePaginationDTO(purchase))
                .toList();
        return new PageDTO<>(purchaseDTO, purchasePage.getNumber(), purchasePage.getSize(),
                purchasePage.getTotalElements());
    }

    @Override
    public Optional<PurchaseFindOneDTO> findOne(Long id) {
        Optional<PurchaseEntity> purchaseOpt = this.repository.findById(id);
        if (!purchaseOpt.isPresent()) {
            throw new ExceptionMessage("Not found purchase id: " + id);
        }
        PurchaseEntity purchase = purchaseOpt.get();
        PurchaseFindOneDTO dto = PurchaseFindOneDTO.builder()
                .id(id)
                .supplier(purchase.getSupplier())
                .orderStatus(purchase.getOrderStatus())
                .store(purchase.getStore())
                .observation(purchase.getObservation())
                .operationDate(purchase.getOperationDate())
                .purchaseDetails(purchase.getPurchaseDetails())
                .exchangeRate(purchase.getExchangeRate())
                .subTotal(purchase.getSubTotal())
                .taxAmount(purchase.getTaxAmount())
                .purchaseAmount(purchase.getPurchaseAmount())
                .createdAt(purchase.getCreatedAt())
                .updatedAt(purchase.getUpdatedAt())
                .status(purchase.getStatus())
                .build();
        return Optional.of(dto);
    }

}
