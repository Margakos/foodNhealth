package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.ProductLipid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductLipidRepository extends JpaRepository<ProductLipid, Long> {

}
