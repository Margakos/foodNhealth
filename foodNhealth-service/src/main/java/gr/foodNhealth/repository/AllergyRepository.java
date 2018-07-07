package gr.foodNhealth.repository;

import gr.foodNhealth.model.Allergy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query("SELECT a FROM Allergy a WHERE a.title LIKE CONCAT('%',?1,'%')")
    Page<Allergy> findByQuery(@Param("query") String query, Pageable pageable);
}