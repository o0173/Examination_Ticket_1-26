package edu.hneu.mjt.zinchenko.ticket_1_26.service;

import edu.hneu.mjt.zinchenko.ticket_1_26.dto.DeveloperDTO;
import edu.hneu.mjt.zinchenko.ticket_1_26.entity.Developer;
import edu.hneu.mjt.zinchenko.ticket_1_26.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final ModelMapper modelMapper;

    public List<DeveloperDTO> getAllDevelopers() {
        return developerRepository.findAll().stream()
                .map(developer -> modelMapper.map(developer, DeveloperDTO.class))
                .collect(Collectors.toList());
    }

    public DeveloperDTO saveDeveloper(DeveloperDTO developerDto) {
        Developer developer = modelMapper.map(developerDto, Developer.class);
        return modelMapper.map(developerRepository.save(developer), DeveloperDTO.class);
    }

    public DeveloperDTO findDeveloperById(Long id) {
        return modelMapper.map(developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found")), DeveloperDTO.class);
    }

    public DeveloperDTO updateDeveloper(Long id, DeveloperDTO developerDTO) {
        Developer existingDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
        modelMapper.map(developerDTO, existingDeveloper);
        return modelMapper.map(developerRepository.save(existingDeveloper), DeveloperDTO.class);
    }

    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
    }
}

