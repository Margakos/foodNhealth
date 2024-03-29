package gr.foodNhealth.model.projection;

import gr.foodNhealth.model.ProductPackage;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "inlinedProductPackage", types = ProductPackage.class)
public interface InlinedProductPackageProjection extends SimpleRoleProjection {

    InlinedProductProjection getProduct();

    SimpleRoleProjection getSupermarket();

    BigDecimal getQuantity();

    BigDecimal getPrice();

    Integer getNumPieces();
}
