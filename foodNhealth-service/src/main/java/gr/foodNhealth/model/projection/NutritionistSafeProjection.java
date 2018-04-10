package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.model.Nutritionist.Gender;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nutritionistSafe", types = Nutritionist.class)
public interface NutritionistSafeProjection {

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getComments();

    Boolean getIsActive();

    Gender getGender();
}
