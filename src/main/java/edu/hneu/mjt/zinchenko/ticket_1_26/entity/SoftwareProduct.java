package edu.hneu.mjt.zinchenko.ticket_1_26.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SoftwareProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String version;
    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

}
