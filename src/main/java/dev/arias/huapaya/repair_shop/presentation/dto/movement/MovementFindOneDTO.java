package dev.arias.huapaya.repair_shop.presentation.dto.movement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.InboundEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.OutboundEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MovementFindOneDTO {

    private Long id;

    private MasterDetailEntity reason;

    private StoreEntity originStore;

    private StoreEntity destinationStore;

    private MovementEntity referenceMovement;

    private SaleEntity sale;

    private LocalDate operationDate;

    private String observation;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private BigDecimal movementTotal;

    private List<InboundEntity> inbound;

    private List<OutboundEntity> outbound;

    private LocalDateTime createdAt;

    private Boolean status;

}
