package dev.arias.huapaya.repair_shop.presentation.dto.document_store;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentStoreUpdateDTO {

    private DocumentEntity document;

    private StoreEntity store;

    private String serie;

    private Long number;

}
