package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.FoodCategory;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedFoodCategory", types = FoodCategory.class)
public interface InlinedFoodCategoryProjection {

    Long getId();

    String getTitle();

    String getDescription();

    SimpleRoleProjection getFoodCategoryMainType();

    SimpleRoleProjection getFoodCategorySubType();

    SimpleRoleProjection getMeatCategoryType();
}
