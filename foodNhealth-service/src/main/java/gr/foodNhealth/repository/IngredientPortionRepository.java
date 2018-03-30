package gr.foodNhealth.repository;

import gr.foodNhealth.model.IngredientPortion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IngredientPortionRepository extends JpaRepository<IngredientPortion, Long> {

}
