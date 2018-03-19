package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.proximate.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Proximate extends BaseEntity{

    @OneToOne
    private NutrientsInformation nutrientsInformation;
    
    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Water water;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Energy energy;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Protein protein;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Fat fat;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Carbohydrate carbohydrate;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Sugar sugar;

    @OneToOne(mappedBy = "proximate", cascade = CascadeType.REMOVE)
    private Fibers fibers;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public Carbohydrate getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Carbohydrate carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Sugar getSugar() {
        return sugar;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    public Fibers getFibers() {
        return fibers;
    }

    public void setFibers(Fibers fibers) {
        this.fibers = fibers;
    }
}
