package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.DocumentStoreEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.DocumentRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.DocumentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DocumentImplementation implements DocumentService {

    private final DocumentRepository repository;

    @Transactional(readOnly = false)
    @Override
    public DocumentEntity create(DocumentCreateDTO document) {
        List<DocumentStoreEntity> documentStoreEntities = new ArrayList<>();
        for (DocumentStoreCreateDTO documentStore : document.getDocumentStore()) {
            DocumentStoreEntity updatedDocumentStore = new DocumentStoreEntity();
            updatedDocumentStore.setStore(documentStore.getStore());
            updatedDocumentStore.setSerie(documentStore.getSerie());
            updatedDocumentStore.setNumber(documentStore.getNumber());
            documentStoreEntities.add(updatedDocumentStore);
        }
        DocumentEntity documentCreate = DocumentEntity.builder()
                .name(document.getName())
                .abbreviation(document.getAbbreviation())
                .documentStore(documentStoreEntities)
                .sale(document.getSale())
                .bill(document.getBill())
                .build();
        return this.repository.save(documentCreate);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DocumentFindOneDTO> findOne(Long id) {
        Optional<DocumentEntity> documentOpt = this.repository.findById(id);
        if (!documentOpt.isPresent()) {
            return Optional.empty();
        }
        DocumentEntity document = documentOpt.get();
        List<DocumentStoreFindOneDTO> documentStoreList = new ArrayList<>();
        for (DocumentStoreEntity documentStore : document.getDocumentStore()) {
            DocumentStoreFindOneDTO documentFindOne = DocumentStoreFindOneDTO.builder()
                    .id(documentStore.getId())
                    .store(documentStore.getStore())
                    .serie(documentStore.getSerie())
                    .number(documentStore.getNumber())
                    .createdAt(documentStore.getCreatedAt())
                    .updatedAt(documentStore.getUpdatedAt())
                    .status(documentStore.getStatus())
                    .build();
            documentStoreList.add(documentFindOne);
        }
        DocumentFindOneDTO dto = DocumentFindOneDTO.builder()
                .id(id)
                .name(document.getName())
                .abbreviation(document.getAbbreviation())
                .documentStore(documentStoreList)
                .sale(document.getSale())
                .bill(document.getBill())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .status(document.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Transactional(readOnly = false)
    @Override
    public DocumentEntity update(DocumentUpdateDTO document, Long id) {
        DocumentEntity documentUpdate = this.repository.findById(id).get();
        List<DocumentStoreEntity> documentStoreEntities = new ArrayList<>();
        if (document.getDocumentStore().size() > 0) {
            for (DocumentStoreUpdateDTO documentStore : document.getDocumentStore()) {
                DocumentStoreEntity updatedDocumentStore = new DocumentStoreEntity();
                updatedDocumentStore.setStore(documentStore.getStore());
                updatedDocumentStore.setSerie(documentStore.getSerie());
                updatedDocumentStore.setNumber(documentStore.getNumber());
                documentStoreEntities.add(updatedDocumentStore);
            }
            documentUpdate.setDocumentStore(documentStoreEntities);
        }
        documentUpdate.setName(document.getName());
        documentUpdate.setAbbreviation(document.getAbbreviation());
        documentUpdate.setSale(document.getSale());
        documentUpdate.setBill(document.getBill());
        return this.repository.save(documentUpdate);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<DocumentPaginationDTO> pagination(Pageable pageable) {
        Page<DocumentEntity> documentPage = this.repository.findAll(pageable);
        List<DocumentPaginationDTO> dto = documentPage.getContent()
                .stream()
                .map(document -> new DocumentPaginationDTO(document))
                .toList();
        return new PageDTO<>(dto, documentPage.getNumber(), documentPage.getSize(), documentPage.getTotalElements());
    }

}
