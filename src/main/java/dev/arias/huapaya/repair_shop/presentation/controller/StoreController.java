package dev.arias.huapaya.repair_shop.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreAllDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StorePaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.store.StoreUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.StoreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "store")
public class StoreController {

    private final StoreService service;

    private final String UPLOAD_DIR = "F:\\upload\\";

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(required = false) MultipartFile file,
            @RequestParam Long currency, @RequestParam String name,
            @RequestParam String address, @RequestParam String phone,
            @RequestParam String logo)
            throws IllegalStateException, IOException {
        MasterDetailEntity detailCurrency = MasterDetailEntity.builder()
                .id(currency)
                .build();
        StoreCreateDTO dtoStoreCreate = StoreCreateDTO.builder()
                .currency(detailCurrency)
                .name(name)
                .address(address)
                .phone(phone)
                .logo(logo)
                .build();
        Map<String, Object> response = new HashMap<>();
        if (!logo.equals("")) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = logo;
            String destination = this.UPLOAD_DIR + fileName;
            File destinationFile = new File(destination);
            file.transferTo(destinationFile);
        }
        this.service.create(dtoStoreCreate);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<StorePaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @GetMapping(path = "all")
    public ResponseEntity<?> findAll() {
        List<StoreAllDTO> findAll = this.service.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        StoreFindOneDTO store = this.service.findOne(id).get();
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam(required = false) MultipartFile file,
            @RequestParam Long currency, @RequestParam String name,
            @RequestParam String address, @RequestParam String phone,
            @RequestParam String logo)
            throws IllegalStateException, IOException {
        Map<String, Object> response = new HashMap<>();

        MasterDetailEntity detailCurrency = MasterDetailEntity.builder()
                .id(currency)
                .build();
        StoreUpdateDTO dtoStoreUpdate = StoreUpdateDTO.builder()
                .currency(detailCurrency)
                .name(name)
                .address(address)
                .phone(phone)
                .logo(logo)
                .build();
        if (!logo.equals("")) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = logo;
            File destinationFile = new File(this.UPLOAD_DIR + fileName);
            file.transferTo(destinationFile);
        }
        this.service.update(dtoStoreUpdate, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
