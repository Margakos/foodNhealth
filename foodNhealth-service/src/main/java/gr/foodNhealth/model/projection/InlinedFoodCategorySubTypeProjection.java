package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedFoodCategorySubType", types = FoodCategorySubType.class)
public interface InlinedFoodCategorySubTypeProjection extends SimpleRoleProjection {

    SimpleRoleProjection getFoodCategoryCoreType();
}
