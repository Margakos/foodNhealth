package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedProduct", types = Product.class)
public interface InlinedProductProjection extends SimpleRoleProjection {

    String getName();

    String getPhotoPath();

    Product.AvailableForm getAvailableForm();

    SimpleRoleProjection getProductNutrientsInformation();

    InlinedIngredientProjection getIngredient();
}
