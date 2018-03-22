package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.NutrientsInformation;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "inlinedNutrientsInformation", types = NutrientsInformation.class)
public interface InlinedNutrientsInformationProjection {

    Long getId();

    String getTitle();

    String getDescription();

    Collection<InlinedProximateProjection> getProximates();

    Collection<InlinedMineralProjection> getMinerals();

    Collection<InlinedVitaminProjection> getVitamins();

    Collection<InlinedLipidProjection> getLipids();

    Collection<InlinedOtherNutrientProjection> getOtherNutrients();
}
