package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.CreditNoteEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.CreditNoteRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.SaleBillRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNoteCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNoteFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNotePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.InboundOutboundCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.movement.MovementCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.CreditNoteService;
import dev.arias.huapaya.repair_shop.service.interfaces.MovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreditNoteImplementation implements CreditNoteService {

    private final CreditNoteRepository repository;

    private final MovementService movementService;

    private final MasterDetailRepository masterDetailRepository;

    private final SaleBillRepository saleBillRepository;

    @Override
    public CreditNoteEntity create(CreditNoteCreateDTO creditNote) {
        SaleBillEntity saleBill = this.saleBillRepository.findById(creditNote.getSaleBill().getId()).get();
        List<InboundOutboundCreateDTO> inboundList = new ArrayList<>();
        for (SaleDetailEntity details : saleBill.getSale().getSaleDetails()) {
            InboundOutboundCreateDTO inbound = InboundOutboundCreateDTO.builder()
                    .product(details.getProduct())
                    .quantity(details.getQuantity())
                    .price(details.getPrice())
                    .build();
            inboundList.add(inbound);
        }

        Optional<MasterDetailEntity> reason = this.masterDetailRepository.findById(66L);
        MovementCreateDTO movementDto = MovementCreateDTO.builder()
                .reason(reason.get())
                .originStore(creditNote.getStore())
                .destinationStore(null)
                .referenceMovement(null)
                .sale(saleBill.getSale())
                .operationDate(creditNote.getOperationDate())
                .observation(reason.get().getDescription())
                .exchangeRate(creditNote.getExchangeRate())
                .inboundOutbound(inboundList)
                .build();
        MovementEntity movement = this.movementService.create(movementDto);
        if (movement.getId() == 0) {
            throw new ExceptionMessage("Error creating Credit Note Movement");
        }

        CreditNoteEntity creditNoteCreate = CreditNoteEntity.builder()
                .document(creditNote.getDocument())
                .saleBill(saleBill)
                .movement(movement)
                .client(creditNote.getClient())
                .store(creditNote.getStore())
                .typeCreditNote(creditNote.getTypeCreditNote())
                .serie(creditNote.getSerie())
                .number(creditNote.getNumber())
                .amount(creditNote.getAmount())
                .exchangeRate(creditNote.getExchangeRate())
                .tax(creditNote.getTax())
                .operationDate(creditNote.getOperationDate())
                .build();
        return this.repository.save(creditNoteCreate);
    }

    @Override
    public PageDTO<CreditNotePaginationDTO> pagination(Pageable pageable) {
        Page<CreditNoteEntity> creditNotePage = this.repository.findAll(pageable);
        List<CreditNotePaginationDTO> pagination = creditNotePage.getContent().stream()
                .map(creditNOte -> new CreditNotePaginationDTO(creditNOte))
                .toList();
        return new PageDTO<>(pagination, creditNotePage.getNumber(), creditNotePage.getSize(),
                creditNotePage.getTotalElements());
    }

    @Override
    public Optional<CreditNoteFindOneDTO> findOne(Long id) {
        Optional<CreditNoteEntity> creditNoteOpt = this.repository.findById(id);
        if (!creditNoteOpt.isPresent()) {
            throw new ExceptionMessage("Not found credit note id: " + id);
        }
        CreditNoteEntity creditNote = creditNoteOpt.get();
        CreditNoteFindOneDTO dto = CreditNoteFindOneDTO.builder()
                .id(id)
                .document(creditNote.getDocument())
                .saleBill(creditNote.getSaleBill())
                .movement(creditNote.getMovement())
                .client(creditNote.getClient())
                .typeCreditNote(creditNote.getTypeCreditNote())
                .serie(creditNote.getSerie())
                .number(creditNote.getNumber())
                .taxAmount(creditNote.getTaxAmount())
                .subTotal(creditNote.getSubTotal())
                .amount(creditNote.getAmount())
                .exchangeRate(creditNote.getExchangeRate())
                .tax(creditNote.getTax())
                .operationDate(creditNote.getOperationDate())
                .observation(creditNote.getObservation())
                .status(creditNote.getStatus())
                .build();
        return Optional.of(dto);
    }

}
