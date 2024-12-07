package dev.arias.huapaya.repair_shop.presentation.dto.document;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentPaginationDTO {

    private Long id;

    private String name;

    private String abbreviation;

    private Boolean status;

    public DocumentPaginationDTO(DocumentEntity document) {
        this.id = document.getId();
        this.name = document.getName();
        this.abbreviation = document.getAbbreviation();
        this.status = document.getStatus();
    }

}
