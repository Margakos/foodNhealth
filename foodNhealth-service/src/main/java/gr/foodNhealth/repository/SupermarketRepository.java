package gr.foodNhealth.repository;

import gr.foodNhealth.model.Supermarket;
import gr.foodNhealth.model.foodCategory.FoodCategoryCoreType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    @Query("SELECT s FROM Supermarket s WHERE s.title LIKE CONCAT('%',?1,'%') ")
    Page<Supermarket> findByQuery(@Param("query") String query, Pageable pageable);
}
