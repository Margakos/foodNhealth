package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.nutrientsInformation.Mineral;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedMineral", types = Mineral.class)
public interface InlinedMineralProjection {

    Long getId();

    String getTitle();

    String getDescription();
    
    BigDecimal getQuantity();
    
    SimpleRoleProjection getMineralType();

//    InlinedNutrientsInformationProjection getNutrientsInformation();
}
