package dev.arias.huapaya.repair_shop.presentation.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.CreditNoteEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PettyCashEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PurchaseBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleBillEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PaymentFindOneDTO {
    
    private Long id;

    private MasterDetailEntity typeOperation;

    private PettyCashEntity pettyCash;

    private SaleEntity sale;

    private MasterDetailEntity methodPayment;

    private MasterDetailEntity cardType;

    private CreditNoteEntity creditNote;

    private PurchaseBillEntity purchaseBill;

    private SaleBillEntity saleBill;

    private StoreEntity store;

    private LocalDate operationDate;

    private String observation;

    private Boolean invoiced;

    private BigDecimal exchangeRate;

    private BigDecimal taxAmount;

    private BigDecimal subTotal;

    private BigDecimal amount;

    private Boolean impactPettyCash;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

}
