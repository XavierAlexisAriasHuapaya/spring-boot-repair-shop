package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurcharseCreateDTO;
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
    public PurchaseEntity create(PurcharseCreateDTO purcharse) {

        List<PurchaseDetailEntity> purchaseDetailEntities = new ArrayList<>();
        List<InboundOutboundCreateDTO> outboundList = new ArrayList<>();
        for (PurchaseDetailCreateDTO details : purcharse.getPurchaseDetails()) {
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

        PurchaseEntity purcharseCreate = PurchaseEntity.builder()
                .supplier(purcharse.getSupplier())
                .orderStatus(purcharse.getOrderStatus())
                .store(purcharse.getStore())
                .observation(purcharse.getObservation())
                .operationDate(purcharse.getOperationDate())
                .purchaseDetails(purchaseDetailEntities)
                .exchangeRate(purcharse.getExchangeRate())
                .build();
        purcharseCreate = this.repository.save(purcharseCreate);

        if (purcharseCreate.getId() == 0) {
            throw new ExceptionMessage("Error creating Purcharse");
        }

        Optional<MasterDetailEntity> reason = this.masterDetailRepository.findById(25L);
        MovementCreateDTO movementDto = MovementCreateDTO.builder()
                .reason(reason.get())
                .originStore(purcharse.getStore())
                .destinationStore(null)
                .referenceMovement(null)
                .sale(null)
                .operationDate(purcharse.getOperationDate())
                .observation(purcharse.getObservation())
                .exchangeRate(purcharse.getExchangeRate())
                .inboundOutbound(outboundList)
                .build();
        MovementEntity movement = this.movementService.create(movementDto);
        if (movement.getId() == 0) {
            throw new ExceptionMessage("Error creating Purchase Movement");
        }

        return purcharseCreate;
    }

    @Override
    public PageDTO<PurchasePaginationDTO> pagination(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagination'");
    }

    @Override
    public Optional<PurchaseFindOneDTO> findOne(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

}
