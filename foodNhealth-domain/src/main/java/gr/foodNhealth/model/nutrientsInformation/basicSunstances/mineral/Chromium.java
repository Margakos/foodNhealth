package gr.foodNhealth.model.nutrientsInformation.basicSunstances.mineral;

import gr.foodNhealth.model.BaseSubstancesEntity;
import gr.foodNhealth.model.nutrientsInformation.Mineral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Chromium extends BaseSubstancesEntity{

    @OneToOne
    private Mineral mineral;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 10, nullable = false)
    private BigDecimal value;

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.divide(BigDecimal.valueOf(1000000.00), 10);
    }

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }
}
