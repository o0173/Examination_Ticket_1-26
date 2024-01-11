package edu.hneu.mjt.zinchenko.ticket_1_26.controller;

import edu.hneu.mjt.zinchenko.ticket_1_26.dto.DeveloperDTO;
import edu.hneu.mjt.zinchenko.ticket_1_26.dto.SoftwareProductDTO;
import edu.hneu.mjt.zinchenko.ticket_1_26.entity.SoftwareProduct;
import edu.hneu.mjt.zinchenko.ticket_1_26.service.DeveloperService;
import edu.hneu.mjt.zinchenko.ticket_1_26.service.SoftwareProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/software-company")
public class SoftwareController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private SoftwareProductService softwareProductService;

    @GetMapping("/developers/{developerId}/products")
    public ResponseEntity<List<SoftwareProductDTO>> getProductsByDeveloperId(@PathVariable Long developerId) {
        List<SoftwareProductDTO> products = softwareProductService.findProductsByDeveloperId(developerId);
        DeveloperDTO developerDTO = developerService.findDeveloperById(developerId);
        List<SoftwareProductDTO> dtos = products.stream()
                .map(product -> new SoftwareProductDTO(product.getName(), product.getVersion(), product.getProgrammingLanguage(), developerDTO))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/developers")
    public ResponseEntity<DeveloperDTO> createDeveloper(@Valid @RequestBody DeveloperDTO developer) {
        DeveloperDTO savedDeveloper = developerService.saveDeveloper(developer);
        return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
    }

    @PostMapping("/developers/{developerId}/products")
    public ResponseEntity<SoftwareProduct> createProductForDeveloper(
            @PathVariable Long developerId,
            @RequestBody SoftwareProductDTO productDTO) {

        DeveloperDTO developerDTO = developerService.findDeveloperById(developerId);
        System.out.println(developerDTO);
        productDTO.setDeveloperDTO(developerDTO);
        return new ResponseEntity<>(softwareProductService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/product-count/{developerId}")
    public ResponseEntity<Integer> getProductCount(@PathVariable Long developerId){
        List<SoftwareProductDTO> products = softwareProductService.findProductsByDeveloperId(developerId);
        return new ResponseEntity<>(products.size(), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap((error)-> ((FieldError) error).getField(), (error) -> "Error: " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}

