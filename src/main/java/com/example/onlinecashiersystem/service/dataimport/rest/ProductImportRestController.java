package com.example.onlinecashiersystem.service.dataimport.rest;

import com.example.onlinecashiersystem.service.dataimport.service.ProductImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/products")
public class ProductImportRestController {

    private final ProductImportService productImportService;

    public ProductImportRestController(ProductImportService productImportService) {
        this.productImportService = productImportService;
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> importProducts(
            @PathVariable("id") Long productPlaneId,
            @RequestParam("file") MultipartFile file
    ) {
        productImportService.importData(productPlaneId, file);
        return ResponseEntity.ok("The products has been imported!");
    }
}
