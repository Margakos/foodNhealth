package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Lipid extends BaseEntity {

    @NotNull
    @ManyToOne
    private NutrientsInformation nutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String lipidType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public Lipid () {}

    public Lipid (String lipidType) {
        this.lipidType = lipidType;
        this.quantity = BigDecimal.ZERO;
    }

    public Lipid (String lipidType, NutrientsInformation nutrientsInformation) {
        this.lipidType = lipidType;
        this.quantity = BigDecimal.ZERO;
        this.nutrientsInformation = nutrientsInformation;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
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
