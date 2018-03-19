package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.lipid.Monounsaturated;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.lipid.Polyunsaturated;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.lipid.Saturated;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.lipid.Trans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Lipid extends BaseEntity {

    @OneToOne
    private NutrientsInformation nutrientsInformation;

    @OneToOne(mappedBy = "lipid", cascade = CascadeType.REMOVE)
    private Monounsaturated monounsaturated;

    @OneToOne(mappedBy = "lipid", cascade = CascadeType.REMOVE)
    private Polyunsaturated polyunsaturated;

    @OneToOne(mappedBy = "lipid", cascade = CascadeType.REMOVE)
    private Saturated saturated;

    @OneToOne(mappedBy = "lipid", cascade = CascadeType.REMOVE)
    private Trans trans;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Monounsaturated getMonounsaturated() {
        return monounsaturated;
    }

    public void setMonounsaturated(Monounsaturated monounsaturated) {
        this.monounsaturated = monounsaturated;
    }

    public Polyunsaturated getPolyunsaturated() {
        return polyunsaturated;
    }

    public void setPolyunsaturated(Polyunsaturated polyunsaturated) {
        this.polyunsaturated = polyunsaturated;
    }

    public Saturated getSaturated() {
        return saturated;
    }

    public void setSaturated(Saturated saturated) {
        this.saturated = saturated;
    }

    public Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }
}
