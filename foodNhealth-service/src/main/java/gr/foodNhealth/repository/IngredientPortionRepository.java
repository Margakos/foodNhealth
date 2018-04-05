package gr.foodNhealth.repository;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.model.SelectedProductPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IngredientPortionRepository extends JpaRepository<IngredientPortion, Long> {

    @Query("SELECT ip FROM IngredientPortion ip WHERE " +
            "ip.recipe=(SELECT spp.selectedRecipe.recipe FROM SelectedProductPackage spp WHERE spp=?1) AND " +
            "ip.ingredient=(SELECT spp.productPackage.product.ingredient FROM SelectedProductPackage spp WHERE spp=?1)")
    IngredientPortion findBySelectedProductPackage(SelectedProductPackage selectedProductPackage);
}
