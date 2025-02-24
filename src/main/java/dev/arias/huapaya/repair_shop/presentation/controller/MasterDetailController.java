package dev.arias.huapaya.repair_shop.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterDetailService;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping(path = "master-detail")
public class MasterDetailController {

    private final MasterDetailService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MasterDetailCreateDTO create) {
        Map<String, Object> response = new HashMap<>();
        try {
            this.service.create(create);
            response.put("message", "successfully created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ExceptionMessage e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody MasterDetailUpdateDTO detail, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        this.service.update(detail, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
