package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.SelectedRecipe;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedSelectedRecipe", types = SelectedRecipe.class)
public interface InlinedSelectedRecipeProjection extends SimpleRoleProjection {

    String getName();

    InlinedRecipeProjection getRecipe();

    SimpleRoleProjection getNutrientsInformation();
}
