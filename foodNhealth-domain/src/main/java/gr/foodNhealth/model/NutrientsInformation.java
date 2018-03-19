package gr.foodNhealth.model;

import gr.foodNhealth.model.nutrientsInformation.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class NutrientsInformation extends BaseEntity {

    @OneToOne
    private Ingredient ingredient;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Mineral mineral;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Proximate proximate;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Vitamin vitamin;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Lipid lipid;

    @OneToOne(mappedBy = "nutrientsInformation")
    private OtherNutrient OtherNutrient;


    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    public Proximate getProximate() {
        return proximate;
    }

    public void setProximate(Proximate proximate) {
        this.proximate = proximate;
    }

    public Vitamin getVitamin() {
        return vitamin;
    }

    public void setVitamin(Vitamin vitamin) {
        this.vitamin = vitamin;
    }

    public Lipid getLipid() {
        return lipid;
    }

    public void setLipid(Lipid lipid) {
        this.lipid = lipid;
    }

    public OtherNutrient getOtherNutrient() {
        return OtherNutrient;
    }

    public void setOtherNutrient(OtherNutrient OtherNutrient) {
        this.OtherNutrient = OtherNutrient;
    }
}
