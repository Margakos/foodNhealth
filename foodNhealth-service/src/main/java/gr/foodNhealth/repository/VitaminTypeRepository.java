package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.VitaminType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface VitaminTypeRepository extends JpaRepository<VitaminType, Long> {

    @Query("SELECT vt FROM VitaminType vt WHERE vt.title LIKE CONCAT('%',?1,'%') ")
    Page<VitaminType> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT vt FROM VitaminType vt WHERE vt NOT IN " +
            "(SELECT v.vitaminType FROM Vitamin v WHERE v.nutrientsInformation.ingredient.id=?1)")
    Collection<VitaminType> findNotParticipating(@Param("ingredientId") Long ingredientId);
}
