package dev.arias.huapaya.repair_shop.presentation.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase.details.PurchaseDetailPagiantionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PurchasePaginationDTO {

    private Long id;

    private String supplier;

    private String orderStatus;

    private String store;

    private LocalDate operationDate;

    private List<PurchaseDetailPagiantionDTO> purchaseDetails;

    private BigDecimal purchaseAmount;

    public PurchasePaginationDTO(PurchaseEntity purchase) {

        List<PurchaseDetailPagiantionDTO> purchaseDetailDTOList = new ArrayList<>();
        for (PurchaseDetailEntity details : purchase.getPurchaseDetails()) {
            PurchaseDetailPagiantionDTO purchaseDetailDTO = PurchaseDetailPagiantionDTO.builder()
                    .product(details.getProduct().getName())
                    .description(new StringBuilder(details.getProduct().getCategory().getDescription())
                            .append("-")
                            .append(details.getProduct().getModel().getDescription())
                            .append("-")
                            .append(details.getProduct().getBrand().getDescription())
                            .toString())
                    .totalAmount(details.getTotalAmount())
                    .build();
            purchaseDetailDTOList.add(purchaseDetailDTO);
        }

        this.id = purchase.getId();
        this.supplier = purchase.getSupplier().getCompanyName();
        this.orderStatus = purchase.getOrderStatus().getDescription();
        this.store = purchase.getStore().getName();
        this.operationDate = purchase.getOperationDate();
        this.purchaseDetails = purchaseDetailDTOList;
        this.purchaseAmount = purchase.getPurchaseAmount();
    }

}
