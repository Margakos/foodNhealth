package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.LipidType;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface OtherNutrientTypeRepository extends JpaRepository<OtherNutrientType, Long> {

    @Query("SELECT ont FROM OtherNutrientType ont WHERE ont.title LIKE CONCAT('%',?1,'%') ")
    Page<OtherNutrientType> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT ot FROM OtherNutrientType ot WHERE ot NOT IN " +
            "(SELECT o.otherNutrientType FROM OtherNutrient o WHERE o.nutrientsInformation.ingredient.id=?1)")
    Collection<OtherNutrientType> findNotParticipating(@Param("ingredientId") Long ingredientId);
}
