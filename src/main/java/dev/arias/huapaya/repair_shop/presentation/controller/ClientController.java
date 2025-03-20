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

import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.ClientService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "customer")
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<ClientFindOneDTO> client = this.service.findOne(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody ClientUpdateDTO client, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(client, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<ClientPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
