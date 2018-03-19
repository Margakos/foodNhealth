package gr.foodNhealth.model.nutrientsInformation.basicSunstances.proximate;

import gr.foodNhealth.model.BaseSubstancesEntity;
import gr.foodNhealth.model.nutrientsInformation.Proximate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Water extends BaseSubstancesEntity{

    @OneToOne
    private Proximate proximate;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 10, nullable = false)
    private BigDecimal value;

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Proximate getProximate() {
        return proximate;
    }

    public void setProximate(Proximate proximate) {
        this.proximate = proximate;
    }
}
