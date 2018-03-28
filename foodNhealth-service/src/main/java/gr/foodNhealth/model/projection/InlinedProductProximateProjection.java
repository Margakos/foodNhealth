package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.ProductProximate;
import gr.foodNhealth.model.nutrientsInformation.Proximate;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductProximate", types = ProductProximate.class)
public interface InlinedProductProximateProjection extends SimpleRoleProjection {

    BigDecimal getQuantity();

    String getProximateType();

    SimpleRoleProjection getProductNutrientsInformation();
}
