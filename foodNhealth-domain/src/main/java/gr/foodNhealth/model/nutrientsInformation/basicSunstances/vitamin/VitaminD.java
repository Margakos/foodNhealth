package gr.foodNhealth.model.nutrientsInformation.basicSunstances.vitamin;

import gr.foodNhealth.model.BaseSubstancesEntity;
import gr.foodNhealth.model.nutrientsInformation.Vitamin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class VitaminD extends BaseSubstancesEntity {

    @OneToOne
    private Vitamin vitamin;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 10, nullable = false)
    private BigDecimal value;

    public Vitamin getVitamin() {
        return vitamin;
    }

    public void setVitamin(Vitamin vitamin) {
        this.vitamin = vitamin;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.divide(BigDecimal.valueOf(1000.00), 10);
    }
}
