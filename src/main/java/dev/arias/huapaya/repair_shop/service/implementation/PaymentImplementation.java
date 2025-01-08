package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.PaymentRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.PaymentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PaymentImplementation implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public PaymentEntity create(PaymentCreateDTO payment) {
        PaymentEntity paymentCreate = PaymentEntity.builder()
                .typeOperation(payment.getTypeOperation())
                .pettyCash(payment.getPettyCash())
                .sale(payment.getSale())
                .purchase(payment.getPurchase())
                .methodPayment(payment.getMethodPayment())
                .cardType(payment.getCardType())
                .creditNote(payment.getCreditNote())
                .purchaseBill(payment.getPurchaseBill())
                .saleBill(payment.getSaleBill())
                .store(payment.getStore())
                .operationDate(payment.getOperationDate())
                .observation(payment.getObservation())
                .exchangeRate(payment.getExchangeRate())
                .amount(payment.getAmount())
                .impactPettyCash(payment.getImpactPettyCash())
                .build();
        return repository.save(paymentCreate);
    }

    @Override
    public Optional<PaymentFindOneDTO> findOne(Long id) {
        Optional<PaymentEntity> paymentOpt = this.repository.findById(id);
        if (!paymentOpt.isPresent()) {
            throw new ExceptionMessage("Not found payment id: " + id);
        }
        PaymentEntity payment = paymentOpt.get();
        PaymentFindOneDTO dto = PaymentFindOneDTO.builder()
                .id(id)
                .typeOperation(payment.getTypeOperation())
                .pettyCash(payment.getPettyCash())
                .sale(payment.getSale())
                .purchase(payment.getPurchase())
                .methodPayment(payment.getMethodPayment())
                .cardType(payment.getCardType())
                .creditNote(payment.getCreditNote())
                .purchaseBill(payment.getPurchaseBill())
                .saleBill(payment.getSaleBill())
                .store(payment.getStore())
                .operationDate(payment.getOperationDate())
                .observation(payment.getObservation())
                .paid(payment.getPaid())
                .exchangeRate(payment.getExchangeRate())
                .taxAmount(payment.getTaxAmount())
                .subTotal(payment.getSubTotal())
                .amount(payment.getAmount())
                .impactPettyCash(payment.getImpactPettyCash())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .status(payment.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<PaymentPaginationDTO> pagination(Pageable pageable) {
        Page<PaymentEntity> paymentPage = this.repository.findAll(pageable);
        List<PaymentPaginationDTO> paymentList = paymentPage.getContent().stream()
                .map(payment -> new PaymentPaginationDTO(payment))
                .toList();
        return new PageDTO<>(paymentList, paymentPage.getNumber(), paymentPage.getSize(),
                paymentPage.getTotalElements());
    }

}
