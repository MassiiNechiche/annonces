package com.massii.annonce.Repository;

import com.massii.annonce.Entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    @Query("SELECT a FROM Annonce a WHERE (:titre IS NULL OR a.titre LIKE %:titre%) " +
            "AND (:prixMin IS NULL OR a.prix >= :prixMin) " +
            "AND (:prixMax IS NULL OR a.prix <= :prixMax) " +
            "AND (:type IS NULL OR a.type = :type)")
    List<Annonce> search(@Param("titre") String titre,
                         @Param("prixMin") Double prixMin,
                         @Param("prixMax") Double prixMax,
                         @Param("type") Annonce.AnnonceType type);

}
