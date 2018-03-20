package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.ProximateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProximateTypeRepository extends JpaRepository<ProximateType, Long> {

    @Query("SELECT pt FROM ProximateType pt WHERE pt.title LIKE CONCAT('%',?1,'%') ")
    Page<ProximateType> findByQuery(@Param("query") String query, Pageable pageable);
}
