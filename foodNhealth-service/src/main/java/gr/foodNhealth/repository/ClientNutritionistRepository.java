package gr.foodNhealth.repository;

import gr.foodNhealth.model.ClientNutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientNutritionistRepository extends JpaRepository<ClientNutritionist, Long> {
}
