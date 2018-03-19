package gr.foodNhealth.repository;

import gr.foodNhealth.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}
