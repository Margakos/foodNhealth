package gr.foodNhealth.repository;

import gr.foodNhealth.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE" +
            "(c.email LIKE CONCAT('%',?1,'%') OR c.firstName LIKE CONCAT('%',?1,'%')" +
            " OR c.lastName LIKE CONCAT('%',?1,'%') OR c.description LIKE CONCAT('%',?1,'%')" +
            " OR c.address LIKE CONCAT('%',?1,'%') OR c.city LIKE CONCAT('%',?1,'%') OR c.zipCode LIKE CONCAT('%',?1,'%'))" +
            " AND c IN (SELECT cn.client FROM ClientNutritionist cn WHERE cn.nutritionist.id=?2 AND cn.state=gr.foodNhealth.model.ClientNutritionist$State.ACCEPTED)")
    Page<Client> findByQuery(@Param("query") String query, @Param("nutritionistId") Long nutritionistId, Pageable pageable);

    @Query("SELECT c FROM Client c WHERE c.email LIKE CONCAT('%',?1,'%') OR c.firstName LIKE CONCAT('%',?1,'%') OR c.lastName LIKE CONCAT('%',?1,'%')")
    Page<Client> searchByQuery(@Param("query") String query, Pageable pageable);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Client findByEmail(@Param("email") String email);
}