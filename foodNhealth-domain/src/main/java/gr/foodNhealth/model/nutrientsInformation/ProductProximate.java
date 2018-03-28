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
public class ProductProximate extends BaseEntity{

    @NotNull
    @ManyToOne
    private ProductNutrientsInformation productNutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String proximateType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public ProductProximate () {}

    public ProductProximate (String proximateType) {
        this.proximateType = proximateType;
        this.quantity = BigDecimal.ZERO;
    }

    public ProductProximate (String proximateType, ProductNutrientsInformation productNutrientsInformation) {
        this.proximateType = proximateType;
        this.quantity = BigDecimal.ZERO;
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public String getProximateType() {
        return proximateType;
    }

    public void setProximateType(String proximateType) {
        this.proximateType = proximateType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
