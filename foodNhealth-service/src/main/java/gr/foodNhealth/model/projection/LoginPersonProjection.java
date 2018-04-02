package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Person;
import gr.foodNhealth.model.Person.Gender;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "loginPerson", types = {Person.class, Client.class})
public interface LoginPersonProjection {

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getComments();

    Boolean getIsActive();

    Gender getGender();
}
