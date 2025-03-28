package dev.arias.huapaya.repair_shop.presentation.dto.document_store;

import java.time.LocalDateTime;

import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentStoreFindOneDTO {

    private Long id;

    private StoreEntity store;

    private String serie;

    private Long number;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean status;

}
