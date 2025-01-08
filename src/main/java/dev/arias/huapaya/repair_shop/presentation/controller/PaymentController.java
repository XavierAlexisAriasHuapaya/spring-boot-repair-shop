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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.PaymentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentCreateDTO create) {
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

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<PaymentFindOneDTO> payment = this.service.findOne(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<PaymentPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
