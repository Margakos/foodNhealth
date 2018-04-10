package gr.foodNhealth.repository;

import gr.foodNhealth.model.Nutritionist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {

    @Query("SELECT u FROM Nutritionist u WHERE" +
            " u.email LIKE CONCAT('%',?1,'%') OR u.firstName LIKE CONCAT('%',?1,'%')" +
            " OR u.lastName LIKE CONCAT('%',?1,'%') OR u.description LIKE CONCAT('%',?1,'%') ")
    Page<Nutritionist> findByQuery(@Param("query") String query, Pageable pageable);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Nutritionist findByEmail(String email);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Nutritionist findByEmailAndIsActive(String email, boolean isActive);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Nutritionist findByEmailAndPasswordAndIsActive(String email, String password, boolean isActive);
}
