package com.massii.annonce.DTO;

import com.massii.annonce.Entity.Annonce;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnonceDTO {
    private Long id;

    @NotEmpty(message = "Veuillez renseigner un titre")
    private String titre;

    @NotEmpty(message = "Veuillez renseigner une description")
    private String description;

    @Positive(message = "Le prix doit étre supérieur à 0")
    private double prix;

    @NotNull(message = "Veuillez spécifier un type d'annonce")
    @Enumerated(EnumType.STRING)
    private Annonce.AnnonceType type;

    public AnnonceDTO(Annonce annonce) {
        this.id = annonce.getId();
        this.titre = annonce.getTitre();
        this.description = annonce.getDescription();
        this.prix = annonce.getPrix();
        this.type = annonce.getType();
    }
}
