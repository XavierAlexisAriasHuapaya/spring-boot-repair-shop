package dev.arias.huapaya.repair_shop.presentation.controller;

import java.util.HashMap;
import java.util.Map;

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
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBIllPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.purchase_bill.PurchaseBillFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.PurchaseBillService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "purchase-bill")
public class PurchaseBillController {

    private final PurchaseBillService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurchaseBillCreateDTO create) {
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

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<PurchaseBIllPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PurchaseBillFindOneDTO purchaseBill = this.service.findOne(id).get();
            return new ResponseEntity<>(purchaseBill, HttpStatus.OK);
        } catch (ExceptionMessage e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
