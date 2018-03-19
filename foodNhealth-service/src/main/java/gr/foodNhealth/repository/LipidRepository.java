package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.Lipid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LipidRepository extends JpaRepository<Lipid, Long> {

}
