package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.mineral.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Mineral extends BaseEntity {

    @OneToOne
    private NutrientsInformation nutrientsInformation;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Calcium calcium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Chromium chromium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Copper copper;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Fluoride fluoride;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Iodine iodine;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Iron iron;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Magnesium magnesium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Manganese manganese;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Molybdenum molybdenum;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Phosphorus phosphorus;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Selenium selenium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Zinc zinc;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Potassium potassium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Sodium sodium;

    @OneToOne(mappedBy = "mineral", cascade = CascadeType.REMOVE)
    private Chloride chloride;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Calcium getCalcium() {
        return calcium;
    }

    public void setCalcium(Calcium calcium) {
        this.calcium = calcium;
    }

    public Chromium getChromium() {
        return chromium;
    }

    public void setChromium(Chromium chromium) {
        this.chromium = chromium;
    }

    public Copper getCopper() {
        return copper;
    }

    public void setCopper(Copper copper) {
        this.copper = copper;
    }

    public Fluoride getFluoride() {
        return fluoride;
    }

    public void setFluoride(Fluoride fluoride) {
        this.fluoride = fluoride;
    }

    public Iodine getIodine() {
        return iodine;
    }

    public void setIodine(Iodine iodine) {
        this.iodine = iodine;
    }

    public Iron getIron() {
        return iron;
    }

    public void setIron(Iron iron) {
        this.iron = iron;
    }

    public Magnesium getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Magnesium magnesium) {
        this.magnesium = magnesium;
    }

    public Manganese getManganese() {
        return manganese;
    }

    public void setManganese(Manganese manganese) {
        this.manganese = manganese;
    }

    public Molybdenum getMolybdenum() {
        return molybdenum;
    }

    public void setMolybdenum(Molybdenum molybdenum) {
        this.molybdenum = molybdenum;
    }

    public Phosphorus getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Phosphorus phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Selenium getSelenium() {
        return selenium;
    }

    public void setSelenium(Selenium selenium) {
        this.selenium = selenium;
    }

    public Zinc getZinc() {
        return zinc;
    }

    public void setZinc(Zinc zinc) {
        this.zinc = zinc;
    }

    public Potassium getPotassium() {
        return potassium;
    }

    public void setPotassium(Potassium potassium) {
        this.potassium = potassium;
    }

    public Sodium getSodium() {
        return sodium;
    }

    public void setSodium(Sodium sodium) {
        this.sodium = sodium;
    }

    public Chloride getChloride() {
        return chloride;
    }

    public void setChloride(Chloride chloride) {
        this.chloride = chloride;
    }
}