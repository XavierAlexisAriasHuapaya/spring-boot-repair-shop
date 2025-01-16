package dev.arias.huapaya.repair_shop.presentation.dto.movement;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.arias.huapaya.repair_shop.persistence.entity.MovementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MovementPaginationDTO {

    private Long id;

    private String reason;

    private String originStore;

    private String destinationStore;

    private String referenceMovement;

    private String sale;

    private LocalDate operationDate;

    private BigDecimal movementTotal;

    private BigDecimal subTotal;

    private BigDecimal taxAmount;

    private Boolean status;

    public MovementPaginationDTO(MovementEntity movement) {
        this.id = movement.getId();
        this.reason = movement.getReason().getDescription();
        this.originStore = movement.getOriginStore().getName();
        this.destinationStore = movement.getDestinationStore() == null ? "-" : movement.getDestinationStore().getName();
        this.referenceMovement = movement.getReferenceMovement() == null ? "-"
                : movement.getReferenceMovement().getId().toString();
        this.sale = movement.getSale() == null ? "-"
                : new StringBuilder(movement.getSale().getDocument().getName())
                        .append(" ")
                        .append(movement.getSale().getSerie())
                        .append("-")
                        .append(movement.getSale().getNumber())
                        .toString();
        this.operationDate = movement.getOperationDate();
        this.movementTotal = movement.getMovementTotal();
        this.subTotal = movement.getSubTotal();
        this.taxAmount = movement.getTaxAmount();
        this.status = movement.getStatus();
    }

}
