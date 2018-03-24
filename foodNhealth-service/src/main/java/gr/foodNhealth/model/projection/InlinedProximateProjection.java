package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Proximate;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProximate", types = Proximate.class)
public interface InlinedProximateProjection {

    Long getId();

    String getTitle();

    String getDescription();

    BigDecimal getQuantity();

    SimpleRoleProjection getProximateType();

    InlinedNutrientsInformationProjection getNutrientsInformation();
}
