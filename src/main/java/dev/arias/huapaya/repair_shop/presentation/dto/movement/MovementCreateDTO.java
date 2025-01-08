package dev.arias.huapaya.repair_shop.presentation.dto.movement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MovementCreateDTO {

    private SupplierEntity supplier;

    private MasterDetailEntity reason;

    private StoreEntity originStore;

    private StoreEntity destinationStore;

    private MovementEntity referenceMovement;

    private SaleEntity sale;

    private LocalDate operationDate;

    private String observation;

    private BigDecimal exchangeRate;

    private List<InboundOutboundCreateDTO> inboundOutbound;

}