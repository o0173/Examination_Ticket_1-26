package edu.hneu.mjt.zinchenko.ticket_1_26.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoftwareProductDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String version;

    @NotBlank
    private String programmingLanguage;

    @NotBlank
    private DeveloperDTO developerDTO;
}
