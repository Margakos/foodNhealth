package gr.foodNhealth.repository;

import gr.foodNhealth.model.foodCategoty.FoodCategoryParentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FoodCategoryParentTypeRepository extends JpaRepository<FoodCategoryParentType, Long> {

    @Query("SELECT fcpt FROM FoodCategoryParentType fcpt WHERE fcpt.title LIKE CONCAT('%',?1,'%') ")
    Page<FoodCategoryParentType> findByQuery(@Param("query") String query, Pageable pageable);
}
