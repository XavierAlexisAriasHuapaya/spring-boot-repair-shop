package dev.arias.huapaya.repair_shop.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.master.MasterFindOneDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "master")
public class MasterController {

    private final MasterService service;

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        MasterFindOneDTO master = this.service.findOne(id);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

}
