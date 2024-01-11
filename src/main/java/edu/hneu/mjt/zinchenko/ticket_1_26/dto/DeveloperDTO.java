package edu.hneu.mjt.zinchenko.ticket_1_26.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    private Long Id;

    private String name;

    private String programmingLanguages;

    private int experience;

    private String messenger;

    private String phoneNumber;
}
