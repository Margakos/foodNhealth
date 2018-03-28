package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class OtherNutrient extends BaseEntity {

    @NotNull
    @ManyToOne
    private NutrientsInformation nutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String otherNutrientType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;


    public OtherNutrient () {}

    public OtherNutrient (String otherNutrientType) {
        this.otherNutrientType = otherNutrientType;
        this.quantity = BigDecimal.ZERO;
    }

    public OtherNutrient (String otherNutrientType, NutrientsInformation nutrientsInformation) {
        this.otherNutrientType = otherNutrientType;
        this.quantity = BigDecimal.ZERO;
        this.nutrientsInformation = nutrientsInformation;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public String getOtherNutrientType() {
        return otherNutrientType;
    }

    public void setOtherNutrientType(String otherNutrientType) {
        this.otherNutrientType = otherNutrientType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
