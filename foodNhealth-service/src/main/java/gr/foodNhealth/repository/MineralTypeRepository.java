package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.Mineral;
import gr.foodNhealth.model.nutrientsInformation.MineralType;
import gr.foodNhealth.model.nutrientsInformation.VitaminType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MineralTypeRepository extends JpaRepository<MineralType, Long> {

    @Query("SELECT mt FROM MineralType mt WHERE mt.title LIKE CONCAT('%',?1,'%') ")
    Page<MineralType> findByQuery(@Param("query") String query, Pageable pageable);
}
