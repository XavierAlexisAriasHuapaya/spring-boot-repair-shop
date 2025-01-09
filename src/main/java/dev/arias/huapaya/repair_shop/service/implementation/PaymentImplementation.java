package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.PaymentRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.PurchaseBillRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.SaleRepository;
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

    private final SaleRepository saleRepository;

    private final PurchaseBillRepository purchaseBillRepository;

    @Override
    public PaymentEntity create(PaymentCreateDTO payment) {
        Optional<SaleEntity> saleOpt = Optional.empty();
        Optional<PurchaseBillEntity> purchaseBillOpt = Optional.empty();
        PurchaseEntity purchaseFindOne = null;
        PurchaseBillEntity purchaseBillFindOne = null;
        SaleEntity saleFindOne = null;
        if (payment.getSale() != null) {
            saleOpt = this.saleRepository.findById(payment.getSale().getId());
            if (saleOpt.isEmpty()) {
                throw new ExceptionMessage("Not found sale id: " + payment.getSale().getId());
            }
            saleFindOne = saleOpt.get();
        } else if (payment.getPurchaseBill() != null) {
            purchaseBillOpt = this.purchaseBillRepository.findById(payment.getPurchaseBill().getId());
            if (purchaseBillOpt.isEmpty()) {
                throw new ExceptionMessage("Not found purchase bill id: " + payment.getPurchaseBill().getId());
            }
            purchaseBillFindOne = purchaseBillOpt.get();
            purchaseFindOne = purchaseBillOpt.get().getPurchase();
            if (purchaseBillOpt.isEmpty()) {
                throw new ExceptionMessage("Not found purchase");
            }
            purchaseBillFindOne.setPurchase(purchaseFindOne);
            payment.setPurchaseBill(purchaseBillFindOne);
        }
        PaymentEntity paymentCreate = PaymentEntity.builder()
                .typeOperation(payment.getTypeOperation())
                .pettyCash(payment.getPettyCash())
                .sale(saleFindOne)
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
                .methodPayment(payment.getMethodPayment())
                .cardType(payment.getCardType())
                .creditNote(payment.getCreditNote())
                .purchaseBill(payment.getPurchaseBill())
                .saleBill(payment.getSaleBill())
                .store(payment.getStore())
                .operationDate(payment.getOperationDate())
                .observation(payment.getObservation())
                .invoiced(payment.getInvoiced())
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
