package gr.foodNhealth.repository;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE CONCAT('%',?1,'%') ")
    Page<Ingredient> findByQuery(@Param("query") String query, Pageable pageable);
}
