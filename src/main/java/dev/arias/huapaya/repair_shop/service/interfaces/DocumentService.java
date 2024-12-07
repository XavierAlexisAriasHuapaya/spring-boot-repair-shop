package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.DocumentEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;

public interface DocumentService {

    public DocumentEntity create(DocumentCreateDTO document);

    public Optional<DocumentFindOneDTO> findOne(Long id);

    public DocumentEntity update(DocumentUpdateDTO document, Long id);

    public PageDTO<DocumentPaginationDTO> pagination(Pageable pageable);

}
