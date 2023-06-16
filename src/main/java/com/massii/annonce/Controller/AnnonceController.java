package com.massii.annonce.Controller;

import com.massii.annonce.Entity.Annonce;
import com.massii.annonce.Service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/")
    public List<Annonce> getAllAnnonces(){
        return annonceService.fetchAllAnnonces();
    }

    @GetMapping("/{id}")
    public Annonce getAnnonceById(@PathVariable("id") Long id){
        return annonceService.getAnnonceById(id);
    }

    @PostMapping("/")
    public Annonce saveAnnonce(@RequestBody Annonce annonce){
        return annonceService.saveAnnonce(annonce);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnonce(@PathVariable("id") Long id, @RequestBody Annonce annonce){
        return annonceService.updateAnnonceById(id, annonce);
    }

    @DeleteMapping("/{id}")
    public String deleteAnnonce(@PathVariable("id") long id){
        return annonceService.deleteAnnonceById(id);
    }
}

