package gr.foodNhealth.model.nutrientsInformation.basicSunstances.lipid;

import gr.foodNhealth.model.BaseSubstancesEntity;
import gr.foodNhealth.model.nutrientsInformation.Lipid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Polyunsaturated extends BaseSubstancesEntity {

    @OneToOne
    private Lipid lipid;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 10, nullable = false)
    private BigDecimal value;

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.divide(BigDecimal.valueOf(1000.00), 10);
    }

    public Lipid getLipid() {
        return lipid;
    }

    public void setLipid(Lipid lipid) {
        this.lipid = lipid;
    }
}
