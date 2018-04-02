package gr.foodNhealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class SelectedProductPackage extends BaseEntity {

    @ManyToOne
    private ProductPackage productPackage;

    @ManyToOne
    private SelectedRecipe selectedRecipe;

    @Min(0)
    @NotNull
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal quantity;

    public ProductPackage getProductPackage() {
        return productPackage;
}

    public void setProductPackage(ProductPackage productPackage) {
        this.productPackage = productPackage;
    }

    public SelectedRecipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(SelectedRecipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
