package gr.foodNhealth.repository;

import gr.foodNhealth.model.RecipeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {

    @Query("SELECT rc FROM RecipeCategory rc WHERE rc.title LIKE CONCAT('%',?1,'%') ")
    Page<RecipeCategory> findByQuery(@Param("query") String query, Pageable pageable);
}
