package dev.arias.huapaya.repair_shop.presentation.dto.movement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.SaleEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MovementPaginationDTO {

    private Long id;

    private MasterDetailEntity reason;

    private StoreEntity originStore;

    private StoreEntity destinationStore;

    private MovementEntity referenceMovement;

    private SaleEntity sale;

    private LocalDate operationDate;

    private BigDecimal movementTotal;

    private LocalDateTime createdAt;

    private Boolean status;

    public MovementPaginationDTO(MovementEntity movement) {
        this.id = movement.getId();
        this.reason = movement.getReason();
        this.originStore = movement.getOriginStore();
        this.destinationStore = movement.getDestinationStore();
        this.referenceMovement = movement.getReferenceMovement();
        this.sale = movement.getSale();
        this.operationDate = movement.getOperationDate();
        this.movementTotal = movement.getMovementTotal();
        this.createdAt = movement.getCreatedAt();
        this.status = movement.getStatus();
    }

}
