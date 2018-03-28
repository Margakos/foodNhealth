package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Vitamin;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedVitamin", types = Vitamin.class)
public interface InlinedVitaminProjection extends SimpleRoleProjection {
    
    BigDecimal getQuantity();

    String getVitaminType();

    SimpleRoleProjection getNutrientsInformation();
}
