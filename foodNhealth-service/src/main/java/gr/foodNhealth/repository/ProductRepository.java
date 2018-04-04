package gr.foodNhealth.repository;

import gr.foodNhealth.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',?1,'%') ")
    Page<Product> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.ingredient.id IN (SELECT ip.ingredient FROM IngredientPortion ip WHERE ip.recipe.id=?1)")
    List<Product> findProductsByRecipe(@Param("recipeId")Long recipeId);
}
