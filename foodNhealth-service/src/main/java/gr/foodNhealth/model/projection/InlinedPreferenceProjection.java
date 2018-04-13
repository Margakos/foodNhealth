package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Preference;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Collection;

@Projection(name = "inlinedPreference", types = Preference.class)
public interface InlinedPreferenceProjection extends SimpleRoleProjection {

    Collection<SimpleRoleProjection> getAllergies();

    Collection<SimpleRoleProjection> getDislikedIngredients();

    BigDecimal getWeightPerWeek();
}
