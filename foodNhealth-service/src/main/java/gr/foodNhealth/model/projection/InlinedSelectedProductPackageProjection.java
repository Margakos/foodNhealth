package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.SelectedProductPackage;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedSelectedProductPackage", types = SelectedProductPackage.class)
public interface InlinedSelectedProductPackageProjection extends SimpleRoleProjection {

    InlinedProductPackageProjection getProductPackage();

    InlinedSelectedRecipeProjection getSelectedRecipe();

    BigDecimal getQuantity();
}
