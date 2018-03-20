package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.OtherNutrientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OtherNutrientTypeRepository extends JpaRepository<OtherNutrientType, Long> {

    @Query("SELECT ont FROM OtherNutrientType ont WHERE ont.title LIKE CONCAT('%',?1,'%') ")
    Page<OtherNutrientType> findByQuery(@Param("query") String query, Pageable pageable);
}
