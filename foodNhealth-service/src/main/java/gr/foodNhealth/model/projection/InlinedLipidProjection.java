package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Lipid;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedLipid", types = Lipid.class)
public interface InlinedLipidProjection extends SimpleRoleProjection {

    BigDecimal getQuantity();

    String getLipidType();

    SimpleRoleProjection getNutrientsInformation();
}
