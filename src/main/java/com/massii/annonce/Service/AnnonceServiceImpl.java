package com.massii.annonce.Service;

import com.massii.annonce.Entity.Annonce;
import com.massii.annonce.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    @Override
    public Annonce saveAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public List<Annonce> fetchAllAnnonces(){
        return annonceRepository.findAll();
    }

    @Override
    public Annonce getAnnonceById(Long id){
        Optional<Annonce> annonce = annonceRepository.findById(id);
        return annonce.orElse(null);
    }

    @Override
    public ResponseEntity<?> updateAnnonceById(Long id, Annonce annonce){
        Optional<Annonce> annonce1 = annonceRepository.findById(id);
        if(annonce1.isPresent()){
            Annonce originalAnnonce = annonce1.get();
            originalAnnonce.setTitre(annonce.getTitre());
            originalAnnonce.setDescription(annonce.getDescription());
            originalAnnonce.setPrix(annonce.getPrix());
            originalAnnonce.setType(annonce.getType());
            Annonce updatedAnnonce = annonceRepository.save(originalAnnonce);
            return ResponseEntity.ok(updatedAnnonce);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Annonce introuvable");
        }
    }

    @Override
    public String deleteAnnonceById(Long id){
        if(annonceRepository.findById(id).isPresent()){
            annonceRepository.deleteById(id);
            return "Annonce supprimée avec succés";
        }
        return "Annonce introuvable";
    }
}
