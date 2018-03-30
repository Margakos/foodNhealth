package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Recipe;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedRecipe", types = Recipe.class)
public interface InlinedRecipeProjection extends SimpleRoleProjection {

    String getName();

    String getPhotoPath();

    String getInstruction();

    SimpleRoleProjection getRecipeCategory();

    SimpleRoleProjection getCuisine();
}
