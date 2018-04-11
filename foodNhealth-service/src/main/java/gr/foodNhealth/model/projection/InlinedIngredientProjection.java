package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Ingredient;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Collection;

@Projection(name = "inlinedIngredient", types = Ingredient.class)
public interface InlinedIngredientProjection extends SimpleRoleProjection {

    String getName();

    String getPhotoPath();

    Boolean getQuantified();

    SimpleRoleProjection getFoodCategoryCoreType();

    SimpleRoleProjection getFoodCategorySubType();

    SimpleRoleProjection getNutrientsInformation();

    Collection<SimpleRoleProjection> getAllergies();
}
