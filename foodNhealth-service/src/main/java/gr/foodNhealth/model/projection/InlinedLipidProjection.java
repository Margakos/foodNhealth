package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Lipid;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedLipid", types = Lipid.class)
public interface InlinedLipidProjection {

    Long getId();

    String getTitle();

    String getDescription();

    BigDecimal getQuantity();

    SimpleRoleProjection getLipidType();

    InlinedNutrientsInformationProjection getNutrientsInformation();
}
