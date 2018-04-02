package gr.foodNhealth.repository;

import gr.foodNhealth.model.SelectedRecipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SelectedRecipeRepository extends JpaRepository<SelectedRecipe, Long> {

    @Query("SELECT sr FROM SelectedRecipe sr WHERE sr.recipe.name LIKE CONCAT('%',?1,'%') ")
    Page<SelectedRecipe> findByQuery(@Param("query") String query, Pageable pageable);
}
