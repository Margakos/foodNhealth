package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.IngredientPortion;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedIngredientPortion", types = IngredientPortion.class)
public interface InlinedIngredientPortionProjection extends SimpleRoleProjection {

    BigDecimal getQuantity();

    Integer getPieces();

    InlinedIngredientProjection getIngredient();

    InlinedRecipeProjection getRecipe();
}
