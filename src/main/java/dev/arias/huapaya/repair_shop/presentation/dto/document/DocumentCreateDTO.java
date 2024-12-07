package dev.arias.huapaya.repair_shop.presentation.dto.document;

import java.util.List;

import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentCreateDTO {

    private String name;

    private String abbreviation;

    private List<DocumentStoreCreateDTO> documentStore;

}
