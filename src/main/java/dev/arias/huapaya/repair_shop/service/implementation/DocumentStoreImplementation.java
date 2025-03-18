package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.DocumentStoreRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.DocumentStoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DocumentStoreImplementation implements DocumentStoreService {

    private final DocumentStoreRepository repository;

    @Override
    public DocumentStoreEntity create(DocumentStoreCreateDTO documentStore) {
        DocumentStoreEntity documentStoreCreate = DocumentStoreEntity.builder()
                .document(documentStore.getDocument())
                .store(documentStore.getStore())
                .serie(documentStore.getSerie())
                .number(documentStore.getNumber())
                .build();
        return this.repository.save(documentStoreCreate);
    }

    @Override
    public DocumentStoreEntity update(DocumentStoreUpdateDTO documentStore, Long id) {
        Optional<DocumentStoreEntity> documentStoreFindOne = this.repository.findById(id);
        if (!documentStoreFindOne.isPresent()) {
            throw new ExceptionMessage("document store not found");
        }
        DocumentStoreEntity documentStoreUpdate = documentStoreFindOne.get();
        documentStoreUpdate.setDocument(documentStore.getDocument());
        documentStoreUpdate.setStore(documentStore.getStore());
        documentStoreUpdate.setSerie(documentStore.getSerie());
        documentStoreUpdate.setNumber(documentStore.getNumber());
        return this.repository.save(documentStoreUpdate);
    }

}
