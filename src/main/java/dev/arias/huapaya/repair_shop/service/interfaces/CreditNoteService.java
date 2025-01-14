package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.CreditNoteEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNoteCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNoteFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.credit_note.CreditNotePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;

public interface CreditNoteService {

    public CreditNoteEntity create(CreditNoteCreateDTO creditNote);

    public PageDTO<CreditNotePaginationDTO> pagination(Pageable pageable);

    public Optional<CreditNoteFindOneDTO> findOne(Long id);

}
