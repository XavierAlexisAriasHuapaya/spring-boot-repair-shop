package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.SaleBillRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.SaleRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.sale_bill.SaleBillPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.SaleBillService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SaleBillImplementation implements SaleBillService {

    private final SaleBillRepository repository;

    private final SaleRepository saleRepository;

    @Override
    public SaleBillEntity create(SaleBillCreateDTO saleBill) {

        SaleEntity sale = this.saleRepository.findById(saleBill.getSale().getId()).get();

        SaleBillEntity saleBillCreate = SaleBillEntity.builder()
                .sale(sale)
                .document(saleBill.getDocument())
                .client(saleBill.getClient())
                .operationDate(saleBill.getOperationDate())
                .amount(saleBill.getAmount())
                .exchangeRate(sale.getExchangeRate())
                .tax(saleBill.getTax())
                .advanceAmount(saleBill.getAmount().compareTo(sale.getSaleAmount()) == 0 ? false : true)
                .observation(saleBill.getObservation())
                .build();
        return this.repository.save(saleBillCreate);
    }

    @Override
    public PageDTO<SaleBillPaginationDTO> pagination(Pageable pageable) {
        Page<SaleBillEntity> saleBillPage = this.repository.findAll(pageable);
        List<SaleBillPaginationDTO> pagination = saleBillPage.getContent().stream()
                .map(saleBill -> new SaleBillPaginationDTO(saleBill))
                .toList();
        return new PageDTO<>(pagination, saleBillPage.getNumber(), saleBillPage.getSize(),
                saleBillPage.getTotalElements());
    }

    @Override
    public Optional<SaleBillFindOneDTO> findOne(Long id) {
        Optional<SaleBillEntity> saleBillOpt = this.repository.findById(id);
        if (!saleBillOpt.isPresent()) {
            throw new ExceptionMessage("Not found sale bill id: " + id);
        }
        SaleBillEntity saleBill = saleBillOpt.get();
        SaleBillFindOneDTO dto = SaleBillFindOneDTO.builder()
                .id(id)
                .sale(saleBill.getSale())
                .document(saleBill.getDocument())
                .client(saleBill.getClient())
                .operationDate(saleBill.getOperationDate())
                .serie(saleBill.getSerie())
                .number(saleBill.getNumber())
                .amount(saleBill.getAmount())
                .taxAmount(saleBill.getTaxAmount())
                .subTotal(saleBill.getSubTotal())
                .exchangeRate(saleBill.getExchangeRate())
                .tax(saleBill.getTax())
                .advanceAmount(saleBill.getAdvanceAmount())
                .observation(saleBill.getObservation())
                .status(saleBill.getStatus())
                .build();
        return Optional.of(dto);
    }

}
