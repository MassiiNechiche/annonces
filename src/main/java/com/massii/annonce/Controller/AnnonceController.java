package com.massii.annonce.Controller;

import com.massii.annonce.DTO.AnnonceDTO;
import com.massii.annonce.Entity.Annonce;
import com.massii.annonce.Service.AnnonceService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService){
        this.annonceService = annonceService;
    }


    @GetMapping("/")
    public List<AnnonceDTO> getAllAnnonces(){
        return annonceService.fetchAllAnnonces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnonceById(@PathVariable("id") Long id){
        return annonceService.getAnnonceById(id);
    }

    @PostMapping("/")
    public ResponseEntity<AnnonceDTO> saveAnnonce(@RequestBody @Valid AnnonceDTO annonceDTO){
        Annonce savedAnnonce = annonceService.saveAnnonce(annonceDTO);
        return new ResponseEntity<>(convertToAnnonceDTO(savedAnnonce), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnonce(@PathVariable("id") Long id, @RequestBody @Valid AnnonceDTO annonceDTO){
        return annonceService.updateAnnonceById(id, annonceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable("id") long id){
        return annonceService.deleteAnnonceById(id);
    }

    @GetMapping("/search")
    public List<AnnonceDTO> searchAnnonces(@RequestParam(required = false) String titre,
                                           @RequestParam(required = false) Double prixMin,
                                           @RequestParam(required = false) Double prixMax,
                                           @RequestParam(required = false) Annonce.AnnonceType type) {
        return annonceService.searchAnnonces(titre, prixMin, prixMax, type);
    }


    private AnnonceDTO convertToAnnonceDTO(Annonce annonce) {
        // Perform the conversion logic from Annonce to AnnonceDTO
        AnnonceDTO annonceDTO = new AnnonceDTO();
        annonceDTO.setId(annonce.getId());
        annonceDTO.setTitre(annonce.getTitre());
        annonceDTO.setDescription(annonce.getDescription());
        annonceDTO.setPrix(annonce.getPrix());
        annonceDTO.setType(annonce.getType());
        return annonceDTO;
    }

    private boolean isValidAnnonceType(Annonce.AnnonceType type) {
        return type != null && EnumSet.allOf(Annonce.AnnonceType.class).contains(type);
    }
}

