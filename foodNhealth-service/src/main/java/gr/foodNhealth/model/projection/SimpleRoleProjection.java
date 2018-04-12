package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.*;
import gr.foodNhealth.model.foodCategory.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simpleRole", types = {FoodCategoryCoreType.class, NutrientsInformation.class, Supermarket.class,
        RecipeCategory.class, Cuisine.class, Allergy.class, Ingredient.class})
public interface SimpleRoleProjection {

    Long getId();

    String getTitle();

    String getDescription();
}
