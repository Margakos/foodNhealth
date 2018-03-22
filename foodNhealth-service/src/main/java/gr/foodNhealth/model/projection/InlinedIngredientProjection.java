package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.AvailableForm;
import gr.foodNhealth.model.Ingredient;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedIngredient", types = Ingredient.class)
public interface InlinedIngredientProjection {

    Long getId();

    String getTitle();

    String getDescription();

    String getName();

    BigDecimal getQuantity();

    String getPhotoPath();

    AvailableForm getAvailableForm();

    InlinedFoodCategoryProjection getFoodCategory();

    InlinedNutrientsInformationProjection getNutrientsInformation();
}
