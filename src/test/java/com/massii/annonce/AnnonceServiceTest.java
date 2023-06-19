package com.massii.annonce;

import com.massii.annonce.DTO.AnnonceDTO;
import com.massii.annonce.Entity.Annonce;
import com.massii.annonce.Repository.AnnonceRepository;
import com.massii.annonce.Service.AnnonceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class AnnonceServiceTest {

    @Mock
    private AnnonceRepository annonceRepository;

    @InjectMocks
    private AnnonceServiceImpl annonceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAnnonceById() {

        Long annonceId = 1L;

        // Créer un Mock de annonce
        Annonce annonce = Annonce.builder()
                .id(annonceId)
                .titre("Test Annonce")
                .description("Test Description")
                .prix(100.0)
                .type(Annonce.AnnonceType.emploi)
                .build();

        // Configurer le mock repository pour retourner le mock Annonce object
        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(annonce));

        // Executer
        ResponseEntity<?> responseEntity = annonceService.getAnnonceById(annonceId);

        // Verifier la response
        assertNotNull(responseEntity);

        // Avoir le body du response
        AnnonceDTO result = (AnnonceDTO) responseEntity.getBody();

        // Check tout
        assertNotNull(result);
        assertEquals(annonceId, result.getId());
        assertEquals("Test Annonce", result.getTitre());
        assertEquals("Test Description", result.getDescription());
        assertEquals(100.0, result.getPrix());
        assertEquals(Annonce.AnnonceType.emploi, result.getType());
    }

    @Test
    void shouldGetAllAnnonces() {
        Long annonceId = 1L;

        Annonce annonce1 = Annonce.builder()
                .id(annonceId)
                .titre("Title 1")
                .description("Description 1")
                .prix(100.0)
                .type(Annonce.AnnonceType.emploi)
                .build();

        Annonce annonce2 = Annonce.builder()
                .id(2L)
                .titre("Title 2")
                .description("Description 2")
                .prix(200.0)
                .type(Annonce.AnnonceType.véhicule)
                .build();

        when(annonceRepository.findAll()).thenReturn(Arrays.asList(annonce1, annonce2));

        List<AnnonceDTO> result = annonceService.fetchAllAnnonces();

        assertNotNull(result);
        assertEquals(2, result.size());

        AnnonceDTO annonceDTO = result.get(0);
        assertEquals(1L, annonceDTO.getId());
        assertEquals("Title 1", annonceDTO.getTitre());
        assertEquals("Description 1", annonceDTO.getDescription());
    }

    @Test
    void canSaveAnnonce() {
        AnnonceDTO annonceDTO = new AnnonceDTO();
        annonceDTO.setTitre("Test Annonce");
        annonceDTO.setDescription("Test Description");
        annonceDTO.setPrix(100.0);
        annonceDTO.setType(Annonce.AnnonceType.emploi);

        when(annonceRepository.save(any(Annonce.class))).thenAnswer(invocation -> {
            Annonce savedAnnonce = invocation.getArgument(0);
            savedAnnonce.setId(1L);
            return savedAnnonce;
        });

        Annonce savedAnnonceDTO = annonceService.saveAnnonce(annonceDTO);

        assertNotNull(savedAnnonceDTO);
        assertEquals(1L, savedAnnonceDTO.getId());
        assertEquals("Test Annonce", savedAnnonceDTO.getTitre());
        assertEquals("Test Description", savedAnnonceDTO.getDescription());
        assertEquals(100.0, savedAnnonceDTO.getPrix());
        assertEquals(Annonce.AnnonceType.emploi, savedAnnonceDTO.getType());
    }


}
