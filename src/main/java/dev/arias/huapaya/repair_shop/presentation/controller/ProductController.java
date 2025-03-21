package dev.arias.huapaya.repair_shop.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.product.ProductUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.ProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "product")
public class ProductController {

    private final ProductService service;
    private final String UPLOAD_DIR = "E:\\Git\\images\\";

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
            @RequestParam(required = true) Long unitOfMeasure, @RequestParam(required = true) Long category,
            @RequestParam(required = true) Long model, @RequestParam(required = true) Long brand,
            @RequestParam(required = true) String name, @RequestParam(required = true) BigDecimal unitPrice,
            @RequestParam(required = true) BigDecimal purchasePrice,
            @RequestParam(required = true) Integer minimumStock,
            @RequestParam(required = false) String image) throws IllegalStateException, IOException {

        MasterDetailEntity detailUnitOfMeasure = MasterDetailEntity.builder()
                .id(unitOfMeasure).build();
        MasterDetailEntity detailCategory = MasterDetailEntity.builder()
                .id(category).build();
        MasterDetailEntity detailModel = MasterDetailEntity.builder()
                .id(model).build();
        MasterDetailEntity detailBrand = MasterDetailEntity.builder()
                .id(brand).build();

        ProductCreateDTO create = ProductCreateDTO.builder()
                .unitOfMeasure(detailUnitOfMeasure)
                .category(detailCategory)
                .model(detailModel)
                .brand(detailBrand)
                .name(name)
                .unitPrice(unitPrice)
                .purchasePrice(purchasePrice)
                .minimumStock(minimumStock)
                .image(image)
                .build();

        Map<String, Object> response = new HashMap<>();

        if (!image.equals("")) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = image;
            String destination = this.UPLOAD_DIR + fileName;
            File destinationFile = new File(destination);
            file.transferTo(destinationFile);
        }

        this.service.create(create);
        response.put("message", "successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<ProductFindOneDTO> product = this.service.findOne(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestParam(required = false) MultipartFile file,
            @RequestParam(required = true) Long unitOfMeasure, @RequestParam(required = true) Long category,
            @RequestParam(required = true) Long model, @RequestParam(required = true) Long brand,
            @RequestParam(required = true) String name, @RequestParam(required = true) BigDecimal unitPrice,
            @RequestParam(required = true) BigDecimal purchasePrice,
            @RequestParam(required = true) Integer minimumStock,
            @RequestParam(required = false) String image, @PathVariable Long id)
            throws IllegalStateException, IOException {
        Map<String, Object> response = new HashMap<>();

        MasterDetailEntity detailUnitOfMeasure = MasterDetailEntity.builder()
                .id(unitOfMeasure).build();
        MasterDetailEntity detailCategory = MasterDetailEntity.builder()
                .id(category).build();
        MasterDetailEntity detailModel = MasterDetailEntity.builder()
                .id(model).build();
        MasterDetailEntity detailBrand = MasterDetailEntity.builder()
                .id(brand).build();

        ProductUpdateDTO product = ProductUpdateDTO.builder()
                .unitOfMeasure(detailUnitOfMeasure)
                .category(detailCategory)
                .model(detailModel)
                .brand(detailBrand)
                .name(name)
                .unitPrice(unitPrice)
                .purchasePrice(purchasePrice)
                .minimumStock(minimumStock)
                .image(image)
                .build();
        if (!image.equals("")) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = image;
            String destination = this.UPLOAD_DIR + fileName;
            File destinationFile = new File(destination);
            file.transferTo(destinationFile);
        }
        this.service.update(product, id);
        response.put("message", "successfully updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        PageDTO<ProductPaginationDTO> pagination = this.service.pagination(pageable);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
