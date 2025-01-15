package dev.arias.huapaya.repair_shop.presentation.dto.bank_box;

import dev.arias.huapaya.repair_shop.persistence.entity.BankBoxEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BankBoxPaginationDTO {

    private Long id;

    private String store;

    private String name;

    private Boolean status;

    public BankBoxPaginationDTO(BankBoxEntity bankBox) {
        this.id = bankBox.getId();
        this.store = new StringBuilder(bankBox.getStore().getName())
                .append(" (")
                .append(bankBox.getStore().getCurrency().getValue())
                .append(")").toString();
        this.name = bankBox.getName();
        this.status = bankBox.getStatus();
    }

}
