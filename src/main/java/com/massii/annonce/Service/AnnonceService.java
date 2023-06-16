package com.massii.annonce.Service;

import com.massii.annonce.Entity.Annonce;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnnonceService {
    Annonce saveAnnonce(Annonce annonce);
    List<Annonce> fetchAllAnnonces();
    Annonce getAnnonceById(Long id);
    ResponseEntity<?> updateAnnonceById(Long id, Annonce annonce);
    String deleteAnnonceById(Long id);

}
