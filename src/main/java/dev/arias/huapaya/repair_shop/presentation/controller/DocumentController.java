package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document.DocumentUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.DocumentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "document")
public class DocumentController {

    private final DocumentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<DocumentFindOneDTO> document = this.service.findOne(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody DocumentUpdateDTO document, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(document, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<DocumentPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
