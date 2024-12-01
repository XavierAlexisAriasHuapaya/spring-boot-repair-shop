package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.business.BusinessUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.BusinessService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "business")
public class BusinessController {

    private final BusinessService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BusinessCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody BusinessUpdateDTO update, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(update, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<BusinessFindOneDTO> business = this.service.findOne(id);
        return new ResponseEntity<>(business, HttpStatus.OK);
    }

}
