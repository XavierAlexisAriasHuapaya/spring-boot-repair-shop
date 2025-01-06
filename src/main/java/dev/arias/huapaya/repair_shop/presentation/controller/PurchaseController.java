package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.purchase.PurcharseCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.PurchaseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "purchase")
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurcharseCreateDTO create) {
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

}
