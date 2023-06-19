package com.massii.annonce.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private double prix;

    @Enumerated(EnumType.STRING)
    private AnnonceType type;

    public enum AnnonceType {
        Immobilier,
        véhicule,
        emploi
    }

}
