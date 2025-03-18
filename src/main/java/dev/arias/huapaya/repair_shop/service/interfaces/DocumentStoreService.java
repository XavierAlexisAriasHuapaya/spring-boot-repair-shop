package dev.arias.huapaya.repair_shop.service.interfaces;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentStoreEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreUpdateDTO;

public interface DocumentStoreService {

    public DocumentStoreEntity create(DocumentStoreCreateDTO documentStore);

    public DocumentStoreEntity update(DocumentStoreUpdateDTO documentStore, Long id);

}
