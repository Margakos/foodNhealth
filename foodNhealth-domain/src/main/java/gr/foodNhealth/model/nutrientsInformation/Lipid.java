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

    @ManyToOne
    private LipidType lipidType;

    @NotNull
    @Min(0)
    @Column(precision = 10, scale = 10, nullable = false)
    private BigDecimal quantity;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public LipidType getLipidType() {
        return lipidType;
    }

    public void setLipidType(LipidType lipidType) {
        this.lipidType = lipidType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
