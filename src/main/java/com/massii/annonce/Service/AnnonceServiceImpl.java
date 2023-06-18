package com.massii.annonce.Service;

import com.massii.annonce.Entity.Annonce;
import com.massii.annonce.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.massii.annonce.DTO.AnnonceDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceServiceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public Annonce saveAnnonce(AnnonceDTO annonceDTO) {
        Annonce annonce = convertToEntity(annonceDTO);
        return annonceRepository.save(annonce);
    }


    @Override
    public List<AnnonceDTO> fetchAllAnnonces(){
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces.stream()
                .map(AnnonceDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public AnnonceDTO getAnnonceById(Long id){
        Optional<Annonce> annonce = annonceRepository.findById(id);
        return annonce.map(AnnonceDTO::new).orElse(null);
    }
    @Override
    public ResponseEntity<?> updateAnnonceById(Long id, AnnonceDTO annonceDTO){
        Optional<Annonce> annonceOptional = annonceRepository.findById(id);
        if(annonceOptional.isPresent()){
            Annonce originalAnnonce = annonceOptional.get();
            updateEntityWithDTO(originalAnnonce, annonceDTO);
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

    @Override
    public List<AnnonceDTO> searchAnnonces(String titre, Double prixMin, Double prixMax, Annonce.AnnonceType type) {
        List<Annonce> annonces = annonceRepository.search(titre, prixMin, prixMax, type);
        return annonces.stream()
                .map(AnnonceDTO::new)
                .collect(Collectors.toList());
    }


    private Annonce convertToEntity(AnnonceDTO annonceDTO) {
        Annonce annonce = new Annonce();
        annonce.setId(annonceDTO.getId());
        annonce.setTitre(annonceDTO.getTitre());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setPrix(annonceDTO.getPrix());
        annonce.setType(annonceDTO.getType());
        return annonce;
    }

    private void updateEntityWithDTO(Annonce annonce, AnnonceDTO annonceDTO) {
        annonce.setTitre(annonceDTO.getTitre());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setPrix(annonceDTO.getPrix());
        annonce.setType(annonceDTO.getType());
    }
}
