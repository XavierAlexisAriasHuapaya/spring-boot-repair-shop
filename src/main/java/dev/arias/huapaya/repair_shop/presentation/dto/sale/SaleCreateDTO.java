package dev.arias.huapaya.repair_shop.presentation.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.PettyCashEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.sale.details.SaleDetailCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SaleCreateDTO {

    private DocumentEntity document;

    private MasterDetailEntity paymentCondition;

    private PettyCashEntity pettyCash;

    private MasterDetailEntity deliveryStatus;

    private ClientEntity client;

    private StoreEntity store;

    private String serie;

    private Long number;

    private LocalDate operationDate;

    private LocalDate deliveryDate;

    private LocalDate expirationDate;

    private List<SaleDetailCreateDTO> saleDetails;

    private BigDecimal exchangeRate;

    private BigDecimal tax;

    private String observation;

}
