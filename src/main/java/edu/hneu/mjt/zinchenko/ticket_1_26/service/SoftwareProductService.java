package edu.hneu.mjt.zinchenko.ticket_1_26.service;

import edu.hneu.mjt.zinchenko.ticket_1_26.dto.SoftwareProductDTO;
import edu.hneu.mjt.zinchenko.ticket_1_26.entity.SoftwareProduct;
import edu.hneu.mjt.zinchenko.ticket_1_26.repository.SoftwareProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SoftwareProductService {

    private final SoftwareProductRepository softwareProductRepository;
    private final ModelMapper modelMapper;

    public List<SoftwareProductDTO> getAllProducts() {
        return softwareProductRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SoftwareProduct saveProduct(SoftwareProductDTO productDTO) {
        SoftwareProduct softwareProduct = modelMapper.map(productDTO, SoftwareProduct.class);
        return softwareProductRepository.save(softwareProduct);
    }

    public SoftwareProductDTO findProductById(Long id) {
        return modelMapper.map(softwareProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")), SoftwareProductDTO.class);
    }

    public SoftwareProductDTO updateProduct(Long id, SoftwareProductDTO productDTO) {
        SoftwareProduct existingProduct = softwareProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        modelMapper.map(productDTO, existingProduct);
        return modelMapper.map(softwareProductRepository.save(existingProduct), SoftwareProductDTO.class);
    }

    public void deleteProduct(Long id) {
        softwareProductRepository.deleteById(id);
    }

    private SoftwareProductDTO convertToDto(SoftwareProduct product) {
        return modelMapper.map(product, SoftwareProductDTO.class);
    }

    public List<SoftwareProductDTO> findProductsByDeveloperId(Long developerId) {
        List<SoftwareProduct> products = softwareProductRepository.findByDeveloperId(developerId);

        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

