package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Supermarket;
import gr.foodNhealth.model.foodCategory.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simpleRole", types = {FoodCategoryCoreType.class, NutrientsInformation.class, Supermarket.class})
public interface SimpleRoleProjection {

    Long getId();

    String getTitle();

    String getDescription();
}
