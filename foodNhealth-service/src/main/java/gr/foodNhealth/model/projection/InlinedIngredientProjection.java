package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Ingredient;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedIngredient", types = Ingredient.class)
public interface InlinedIngredientProjection {

    Long getId();

    String getName();

    BigDecimal getQuantity();
}
