package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.ProductOtherNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductOtherNutrientRepository extends JpaRepository<ProductOtherNutrient, Long> {

}
