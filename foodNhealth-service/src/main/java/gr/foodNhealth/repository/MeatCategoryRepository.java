package gr.foodNhealth.repository;

import gr.foodNhealth.model.foodCategoty.MeatCategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MeatCategoryRepository extends JpaRepository<MeatCategoryType, Long> {

    @Query("SELECT mct FROM MeatCategoryType mct WHERE mct.title LIKE CONCAT('%',?1,'%') ")
    Page<MeatCategoryType> findByQuery(@Param("query") String query, Pageable pageable);
}
