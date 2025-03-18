package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.document_store.DocumentStoreUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.DocumentStoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "document-store")
public class DocumentStoreController {

    private final DocumentStoreService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentStoreCreateDTO dto) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(dto);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody DocumentStoreUpdateDTO dto, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(dto, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
