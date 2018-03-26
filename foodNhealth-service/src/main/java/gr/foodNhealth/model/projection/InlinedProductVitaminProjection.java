package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.ProductVitamin;
import gr.foodNhealth.model.nutrientsInformation.Vitamin;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductVitamin", types = ProductVitamin.class)
public interface InlinedProductVitaminProjection extends SimpleRoleProjection {
    
    BigDecimal getQuantity();
    
    SimpleRoleProjection getVitaminType();

    SimpleRoleProjection getProductNutrientsInformation();
}
