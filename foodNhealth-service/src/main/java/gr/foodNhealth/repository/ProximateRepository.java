package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.Proximate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProximateRepository extends JpaRepository<Proximate, Long> {

}
