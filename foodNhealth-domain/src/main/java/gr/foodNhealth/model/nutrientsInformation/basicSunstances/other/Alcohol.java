package gr.foodNhealth.model.nutrientsInformation.basicSunstances.other;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.BaseSubstancesEntity;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.model.nutrientsInformation.Vitamin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Alcohol extends BaseSubstancesEntity {

    @OneToOne
    private OtherNutrient OtherNutrient;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 10, nullable = false)
    private BigDecimal value;


    public OtherNutrient getOtherNutrient() {
        return OtherNutrient;
    }

    public void setOtherNutrient(OtherNutrient OtherNutrient) {
        this.OtherNutrient = OtherNutrient;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.divide(BigDecimal.valueOf(1000.00), 10);
    }
}
