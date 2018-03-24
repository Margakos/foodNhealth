package gr.foodNhealth.repository;

import gr.foodNhealth.model.nutrientsInformation.LipidType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface LipidTypeRepository extends JpaRepository<LipidType, Long> {

    @Query("SELECT lt FROM LipidType lt WHERE lt.title LIKE CONCAT('%',?1,'%') ")
    Page<LipidType> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT lt FROM LipidType lt WHERE lt NOT IN " +
            "(SELECT l.lipidType FROM Lipid l WHERE l.nutrientsInformation.ingredient.id=?1)")
    Collection<LipidType> findNotParticipating(@Param("ingredientId") Long ingredientId);
}
