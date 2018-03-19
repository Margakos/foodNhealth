package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MineralRepository extends JpaRepository<Mineral, Long> {

}
