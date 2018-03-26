package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.model.nutrientsInformation.ProductOtherNutrient;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductOtherNutrient", types = ProductOtherNutrient.class)
public interface InlinedProductOtherNutrientProjection extends SimpleRoleProjection {
    
    BigDecimal getQuantity();
    
    SimpleRoleProjection getOtherNutrientType();

    SimpleRoleProjection getProductNutrientsInformation();
}
