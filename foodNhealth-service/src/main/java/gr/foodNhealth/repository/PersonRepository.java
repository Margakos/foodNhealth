package gr.foodNhealth.repository;

import gr.foodNhealth.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {

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
