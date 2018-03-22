package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.foodCategory.*;
import gr.foodNhealth.model.nutrientsInformation.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simpleRole", types = {FoodCategoryMainType.class, FoodCategorySubType.class, MeatCategoryType.class,
        LipidType.class, MineralType.class, OtherNutrientType.class, ProximateType.class, VitaminType.class})
public interface SimpleRoleProjection {

    Long getId();

    String getTitle();

    String getDescription();
}
