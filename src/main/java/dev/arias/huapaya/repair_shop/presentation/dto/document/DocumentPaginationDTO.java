package dev.arias.huapaya.repair_shop.presentation.dto.document;

import java.util.List;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.DocumentStoreEntity;
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

    private List<DocumentStoreEntity> documentStore;

    private Boolean sale;

    private Boolean bill;

    private Boolean status;

    public DocumentPaginationDTO(DocumentEntity document) {
        this.id = document.getId();
        this.name = document.getName();
        this.abbreviation = document.getAbbreviation();
        this.documentStore = document.getDocumentStore();
        this.sale = document.getSale();
        this.bill = document.getBill();
        this.status = document.getStatus();
    }

}
