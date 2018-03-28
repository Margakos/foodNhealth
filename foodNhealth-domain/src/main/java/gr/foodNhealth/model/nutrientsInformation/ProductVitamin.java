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
    @Column(nullable = false)
    private String vitaminType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public ProductVitamin () {}

    public ProductVitamin (String vitaminType) {
        this.vitaminType = vitaminType;
        this.quantity = BigDecimal.ZERO;
    }

    public ProductVitamin (String vitaminType, ProductNutrientsInformation productNutrientsInformation) {
        this.vitaminType = vitaminType;
        this.quantity = BigDecimal.ZERO;
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public String getVitaminType() {
        return vitaminType;
    }

    public void setVitaminType(String vitaminType) {
        this.vitaminType = vitaminType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}