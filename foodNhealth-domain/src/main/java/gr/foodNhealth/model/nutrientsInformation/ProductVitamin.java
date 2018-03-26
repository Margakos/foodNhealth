package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.ProductNutrientsInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ProductVitamin extends BaseEntity {

    @NotNull
    @ManyToOne
    private ProductNutrientsInformation productNutrientsInformation;

    @NotNull
    @ManyToOne(optional = false)
    private VitaminType vitaminType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;


    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public VitaminType getVitaminType() {
        return vitaminType;
    }

    public void setVitaminType(VitaminType vitaminType) {
        this.vitaminType = vitaminType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}