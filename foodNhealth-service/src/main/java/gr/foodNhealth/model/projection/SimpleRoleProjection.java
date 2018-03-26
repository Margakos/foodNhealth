package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.Supermarket;
import gr.foodNhealth.model.foodCategory.*;
import gr.foodNhealth.model.nutrientsInformation.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simpleRole", types = {FoodCategoryCoreType.class, FoodCategorySubType.class, MeatCategoryType.class,
        LipidType.class, MineralType.class, OtherNutrientType.class, ProximateType.class, VitaminType.class,
        NutrientsInformation.class, ProductNutrientsInformation.class, Supermarket.class})
public interface SimpleRoleProjection {

    Long getId();

    String getTitle();

    String getDescription();
}
