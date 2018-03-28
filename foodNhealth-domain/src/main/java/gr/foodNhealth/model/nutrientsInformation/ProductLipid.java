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
public class ProductLipid extends BaseEntity {

    @NotNull
    @ManyToOne
    private ProductNutrientsInformation productNutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String lipidType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public ProductLipid () {}

    public ProductLipid (String lipidType) {
        this.lipidType = lipidType;
        this.quantity = BigDecimal.ZERO;
    }

    public ProductLipid (String lipidType, ProductNutrientsInformation productNutrientsInformation) {
        this.lipidType = lipidType;
        this.quantity = BigDecimal.ZERO;
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public String getLipidType() {
        return lipidType;
    }

    public void setLipidType(String lipidType) {
        this.lipidType = lipidType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
