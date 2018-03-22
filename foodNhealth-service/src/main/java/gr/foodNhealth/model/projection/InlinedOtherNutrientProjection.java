package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedOtherNutrient", types = OtherNutrient.class)
public interface InlinedOtherNutrientProjection {

    Long getId();

    String getTitle();

    String getDescription();
    
    BigDecimal getQuantity();
    
    SimpleRoleProjection getOtherNutrientType();

//    InlinedNutrientsInformationProjection getNutrientsInformation();
}
