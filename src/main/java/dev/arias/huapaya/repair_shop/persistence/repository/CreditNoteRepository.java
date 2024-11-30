package dev.arias.huapaya.repair_shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.arias.huapaya.repair_shop.persistence.entity.CreditNoteEntity;

public interface CreditNoteRepository extends JpaRepository<CreditNoteEntity, Long> {

}
