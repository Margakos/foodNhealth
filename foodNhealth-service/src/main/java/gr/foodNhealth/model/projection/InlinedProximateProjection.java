package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Proximate;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProximate", types = Proximate.class)
public interface InlinedProximateProjection extends SimpleRoleProjection {

    BigDecimal getQuantity();

    String getProximateType();

    SimpleRoleProjection getNutrientsInformation();
}
