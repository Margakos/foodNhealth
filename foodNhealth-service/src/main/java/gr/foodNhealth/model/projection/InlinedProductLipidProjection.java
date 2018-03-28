package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Lipid;
import gr.foodNhealth.model.nutrientsInformation.ProductLipid;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductLipid", types = ProductLipid.class)
public interface InlinedProductLipidProjection extends SimpleRoleProjection {

    BigDecimal getQuantity();

    String getLipidType();

    SimpleRoleProjection getProductNutrientsInformation();
}
