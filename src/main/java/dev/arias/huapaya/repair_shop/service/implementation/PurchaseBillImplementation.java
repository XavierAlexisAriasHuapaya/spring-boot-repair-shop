package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.PurchaseBillRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBIllPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillFindOneDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.PurchaseBillService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseBillImplementation implements PurchaseBillService {

    private final PurchaseBillRepository repository;

    @Override
    public PurchaseBillEntity create(PurchaseBillCreateDTO purchaseBill) {
        PurchaseBillEntity purchaseBillCreate = PurchaseBillEntity.builder()
                .document(purchaseBill.getDocument())
                .purchase(purchaseBill.getPurchase())
                .serie(purchaseBill.getSerie())
                .number(purchaseBill.getNumber())
                .operationDate(purchaseBill.getOperationDate())
                .observation(purchaseBill.getObservation())
                .build();
        return this.repository.save(purchaseBillCreate);
    }

    @Override
    public Optional<PurchaseBillFindOneDTO> findOne(Long id) {
        Optional<PurchaseBillEntity> purchaseBillFind = this.repository.findById(id);
        if (!purchaseBillFind.isPresent()) {
            return Optional.empty();
        }
        PurchaseBillEntity purchaseBill = purchaseBillFind.get();
        PurchaseBillFindOneDTO dto = PurchaseBillFindOneDTO.builder()
                .id(id)
                .document(purchaseBill.getDocument())
                .purchase(purchaseBill.getPurchase())
                .serie(purchaseBill.getSerie())
                .number(purchaseBill.getNumber())
                .operationDate(purchaseBill.getOperationDate())
                .observation(purchaseBill.getObservation())
                .payments(purchaseBill.getPayments())
                .createdAt(purchaseBill.getCreatedAt())
                .status(purchaseBill.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public PageDTO<PurchaseBIllPaginationDTO> pagination(Pageable pageable) {
        Page<PurchaseBillEntity> purchaseBillPage = this.repository.findAll(pageable);
        List<PurchaseBIllPaginationDTO> purchaseBillDTO = purchaseBillPage.getContent().stream()
                .map(purchase -> new PurchaseBIllPaginationDTO(purchase))
                .toList();
        return new PageDTO<>(purchaseBillDTO, purchaseBillPage.getNumber(), purchaseBillPage.getSize(),
                purchaseBillPage.getTotalElements());
    }

}
