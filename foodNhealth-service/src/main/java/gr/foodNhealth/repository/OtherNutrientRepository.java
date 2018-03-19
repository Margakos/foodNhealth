package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OtherNutrientRepository extends JpaRepository<OtherNutrient, Long> {

}
