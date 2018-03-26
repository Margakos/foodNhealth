package gr.foodNhealth.repository;

import gr.foodNhealth.model.ProductPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductPackageRepository extends JpaRepository<ProductPackage, Long> {

}
