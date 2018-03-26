package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.ProductMineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductMineralRepository extends JpaRepository<ProductMineral, Long> {

}
