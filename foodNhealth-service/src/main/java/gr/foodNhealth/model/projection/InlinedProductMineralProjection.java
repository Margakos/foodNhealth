package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Mineral;
import gr.foodNhealth.model.nutrientsInformation.ProductMineral;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductMineral", types = ProductMineral.class)
public interface InlinedProductMineralProjection extends SimpleRoleProjection {
    
    BigDecimal getQuantity();

    String getMineralType();

    SimpleRoleProjection getProductNutrientsInformation();
}
