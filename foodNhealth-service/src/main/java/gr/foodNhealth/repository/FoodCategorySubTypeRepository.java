package gr.foodNhealth.repository;

import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface FoodCategorySubTypeRepository extends JpaRepository<FoodCategorySubType, Long> {

    @Query("SELECT fct FROM FoodCategorySubType fct WHERE fct.title LIKE CONCAT('%',?1,'%') ")
    Page<FoodCategorySubType> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT fcst FROM FoodCategorySubType fcst WHERE fcst.foodCategoryCoreType.id=?1")
    Collection<FoodCategorySubType> findByFoodCategoryCoreTypeId(@Param("foodCategoryCoreTypeId") Long foodCategoryCoreTypeId);
}
