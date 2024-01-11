package edu.hneu.mjt.zinchenko.ticket_1_26.controller;

import edu.hneu.mjt.zinchenko.ticket_1_26.dto.SoftwareProductDTO;
import edu.hneu.mjt.zinchenko.ticket_1_26.service.SoftwareProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SoftwareProductController {

    @Autowired
    private SoftwareProductService softwareProductService;

    @GetMapping
    public ResponseEntity<List<SoftwareProductDTO>> getAllProducts() {
        return ResponseEntity.ok(softwareProductService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftwareProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(softwareProductService.findProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoftwareProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody SoftwareProductDTO productDTO) {
        return ResponseEntity.ok(softwareProductService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        softwareProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
