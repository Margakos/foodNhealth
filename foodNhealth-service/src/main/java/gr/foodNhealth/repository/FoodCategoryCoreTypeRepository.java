package gr.foodNhealth.repository;

import gr.foodNhealth.model.foodCategory.FoodCategoryCoreType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FoodCategoryCoreTypeRepository extends JpaRepository<FoodCategoryCoreType, Long> {

    @Query("SELECT fcpt FROM FoodCategoryCoreType fcpt WHERE fcpt.title LIKE CONCAT('%',?1,'%') ")
    Page<FoodCategoryCoreType> findByQuery(@Param("query") String query, Pageable pageable);
}
