package gr.foodNhealth.repository;

import gr.foodNhealth.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

}