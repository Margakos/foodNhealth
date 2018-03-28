package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Vitamin extends BaseEntity {

    @NotNull
    @ManyToOne
    private NutrientsInformation nutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String vitaminType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public Vitamin () {}

    public Vitamin (String vitaminType) {
        this.vitaminType = vitaminType;
        this.quantity = BigDecimal.ZERO;
    }

    public Vitamin (String vitaminType, NutrientsInformation nutrientsInformation) {
        this.vitaminType = vitaminType;
        this.quantity = BigDecimal.ZERO;
        this.nutrientsInformation = nutrientsInformation;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
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