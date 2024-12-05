package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;

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
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.RolService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "rol")
public class RolController {

    private final RolService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RolCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<RolPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        RolFindOneDTO rol = this.service.findOne(id).get();
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody RolUpdateDTO rol, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(rol, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
