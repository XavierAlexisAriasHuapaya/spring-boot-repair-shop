package dev.arias.huapaya.repair_shop.presentation.dto.document;

import java.time.LocalDateTime;
import java.util.List;

import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreFindOneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentFindOneDTO {

    private Long id;

    private String name;

    private String abbreviation;

    private List<DocumentStoreFindOneDTO> documentStore;

    private Boolean sale;

    private Boolean bill;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean status;

}
