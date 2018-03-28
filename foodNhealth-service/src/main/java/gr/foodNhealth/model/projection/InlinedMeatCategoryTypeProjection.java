package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import gr.foodNhealth.model.foodCategory.MeatCategoryType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedMeatCategoryType", types = MeatCategoryType.class)
public interface InlinedMeatCategoryTypeProjection extends SimpleRoleProjection {

    InlinedFoodCategorySubTypeProjection getFoodCategorySubType();
}
