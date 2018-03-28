package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.ProductNutrientsInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ProductMineral extends BaseEntity {

    @NotNull
    @ManyToOne
    private ProductNutrientsInformation productNutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String mineralType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public ProductMineral () {}

    public ProductMineral (String mineralType) {
        this.mineralType = mineralType;
        this.quantity = BigDecimal.ZERO;
    }

    public ProductMineral (String mineralType, ProductNutrientsInformation productNutrientsInformation) {
        this.mineralType = mineralType;
        this.quantity = BigDecimal.ZERO;
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public String getMineralType() {
        return mineralType;
    }

    public void setMineralType(String mineralType) {
        this.mineralType = mineralType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}