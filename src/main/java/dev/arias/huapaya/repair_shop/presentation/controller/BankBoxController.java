package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxFindAllDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.bank_box.BankBoxUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.BankBoxService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "bank-box")
public class BankBoxController {

    private final BankBoxService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BankBoxCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<BankBoxFindOneDTO> bankBox = this.service.findOne(id);
        return new ResponseEntity<>(bankBox, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<BankBoxFindAllDTO> bankBox = this.service.findAll();
        return new ResponseEntity<>(bankBox, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody BankBoxUpdateDTO bankBox, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(bankBox, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<BankBoxPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
