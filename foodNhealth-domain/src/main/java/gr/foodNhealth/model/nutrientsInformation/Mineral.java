package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Mineral extends BaseEntity {

    @ManyToOne
    private NutrientsInformation nutrientsInformation;

    @NotNull
    @ManyToOne(optional = false)
    private MineralType mineralType;

    @NotNull
    @Min(0)
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal quantity;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public MineralType getMineralType() {
        return mineralType;
    }

    public void setMineralType(MineralType mineralType) {
        this.mineralType = mineralType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}