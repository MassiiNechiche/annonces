package com.massii.annonce.Service;

import com.massii.annonce.Entity.Annonce;
import org.springframework.http.ResponseEntity;
import com.massii.annonce.DTO.AnnonceDTO;
import java.util.List;

public interface AnnonceService {
    Annonce saveAnnonce(AnnonceDTO annonceDTO);
    List<AnnonceDTO> fetchAllAnnonces();
    AnnonceDTO getAnnonceById(Long id);
    ResponseEntity<?> updateAnnonceById(Long id, AnnonceDTO annonceDTO);
    String deleteAnnonceById(Long id);
    List<AnnonceDTO> searchAnnonces(String titre, Double prixMin, Double prixMax, Annonce.AnnonceType type);

}