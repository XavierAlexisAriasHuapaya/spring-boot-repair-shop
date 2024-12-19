package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
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

import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.petty_cash.PettyCashUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.PettyCashService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "petty-cash")
public class PettyCashController {

    private final PettyCashService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PettyCashCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<PettyCashFindOneDTO> pettyCash = this.service.findOne(id);
        return new ResponseEntity<>(pettyCash, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody PettyCashUpdateDTO pettyCash, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(pettyCash, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<PettyCashPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
