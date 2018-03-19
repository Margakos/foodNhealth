package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.vitamin.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Vitamin extends BaseEntity {

    @OneToOne
    private NutrientsInformation nutrientsInformation;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private Carotenoids carotenoids;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private Choline choline;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminA vitaminA;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB1 vitaminB1;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB2 vitaminB2;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB3 vitaminB3;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB5 vitaminB5;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB6 vitaminB6;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB9 vitaminB9;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminB12 vitaminB12;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminC vitaminC;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminD vitaminD;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminE vitaminE;

    @OneToOne(mappedBy = "vitamin", cascade = CascadeType.REMOVE)
    private VitaminK vitaminK;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Carotenoids getCarotenoids() {
        return carotenoids;
    }

    public void setCarotenoids(Carotenoids carotenoids) {
        this.carotenoids = carotenoids;
    }

    public Choline getCholine() {
        return choline;
    }

    public void setCholine(Choline choline) {
        this.choline = choline;
    }

    public VitaminA getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(VitaminA vitaminA) {
        this.vitaminA = vitaminA;
    }

    public VitaminB1 getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(VitaminB1 vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public VitaminB2 getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(VitaminB2 vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public VitaminB3 getVitaminB3() {
        return vitaminB3;
    }

    public void setVitaminB3(VitaminB3 vitaminB3) {
        this.vitaminB3 = vitaminB3;
    }

    public VitaminB5 getVitaminB5() {
        return vitaminB5;
    }

    public void setVitaminB5(VitaminB5 vitaminB5) {
        this.vitaminB5 = vitaminB5;
    }

    public VitaminB6 getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(VitaminB6 vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public VitaminB9 getVitaminB9() {
        return vitaminB9;
    }

    public void setVitaminB9(VitaminB9 vitaminB9) {
        this.vitaminB9 = vitaminB9;
    }

    public VitaminB12 getVitaminB12() {
        return vitaminB12;
    }

    public void setVitaminB12(VitaminB12 vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }

    public VitaminC getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(VitaminC vitaminC) {
        this.vitaminC = vitaminC;
    }

    public VitaminD getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(VitaminD vitaminD) {
        this.vitaminD = vitaminD;
    }

    public VitaminE getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(VitaminE vitaminE) {
        this.vitaminE = vitaminE;
    }

    public VitaminK getVitaminK() {
        return vitaminK;
    }

    public void setVitaminK(VitaminK vitaminK) {
        this.vitaminK = vitaminK;
    }
}
