package gr.foodNhealth.repository;

import gr.foodNhealth.model.NutrientsInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NutrientsInformationRepository extends JpaRepository<NutrientsInformation, Long> {

}
