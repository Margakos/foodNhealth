package gr.foodNhealth.repository;

import gr.foodNhealth.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}