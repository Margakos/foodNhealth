package gr.foodNhealth.repository;

import gr.foodNhealth.model.foodCategoty.FoodCategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FoodCategoryTypeRepository extends JpaRepository<FoodCategoryType, Long> {

    @Query("SELECT fct FROM FoodCategoryType fct WHERE fct.title LIKE CONCAT('%',?1,'%') ")
    Page<FoodCategoryType> findByQuery(@Param("query") String query, Pageable pageable);
}
