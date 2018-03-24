package gr.foodNhealth.repository;

import gr.foodNhealth.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT u FROM Person u WHERE" +
            " u.email LIKE CONCAT('%',?1,'%') OR u.firstName LIKE CONCAT('%',?1,'%')" +
            " OR u.lastName LIKE CONCAT('%',?1,'%') OR u.description LIKE CONCAT('%',?1,'%') ")
    Page<Person> findByQuery(@Param("query") String query, Pageable pageable);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Person findByEmail(String email);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Person findByEmailAndIsActive(String email, boolean isActive);

    //do not export as rest end-point, to avoid disclosing sensitive information, such as password.
    @RestResource(exported = false)
    Person findByEmailAndPasswordAndIsActive(String email, String password, boolean isActive);
}
