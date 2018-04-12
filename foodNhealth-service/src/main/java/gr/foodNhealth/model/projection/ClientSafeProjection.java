package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Client;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "clientSafe", types = Client.class)
public interface ClientSafeProjection {

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    Boolean getIsActive();

    Client.Gender getGender();

    String getAddress();

    String getCity();

    String getZipCode();

    InlinedPreferenceProjection getPreference();
}
