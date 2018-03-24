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

import java.util.Collection;

@RepositoryRestResource
public interface MineralTypeRepository extends JpaRepository<MineralType, Long> {

    @Query("SELECT mt FROM MineralType mt WHERE mt.title LIKE CONCAT('%',?1,'%') ")
    Page<MineralType> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT mt FROM MineralType mt WHERE mt NOT IN " +
            "(SELECT m.mineralType FROM Mineral m WHERE m.nutrientsInformation.ingredient.id=?1)")
    Collection<MineralType> findNotParticipating(@Param("ingredientId") Long ingredientId);
}
