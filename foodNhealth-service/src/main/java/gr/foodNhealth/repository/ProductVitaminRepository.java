package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.ProductVitamin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductVitaminRepository extends JpaRepository<ProductVitamin, Long> {

}
