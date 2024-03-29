package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Proximate extends BaseEntity{

    @NotNull
    @ManyToOne
    private NutrientsInformation nutrientsInformation;

    @NotNull
    @Column(nullable = false)
    private String proximateType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;

    public Proximate () {}

    public Proximate (String proximateType) {
        this.proximateType = proximateType;
        this.quantity = BigDecimal.ZERO;
    }

    public Proximate (String proximateType, NutrientsInformation nutrientsInformation) {
        this.proximateType = proximateType;
        this.quantity = BigDecimal.ZERO;
        this.nutrientsInformation = nutrientsInformation;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
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
