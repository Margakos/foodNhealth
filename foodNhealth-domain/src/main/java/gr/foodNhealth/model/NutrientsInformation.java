package gr.foodNhealth.model;

import gr.foodNhealth.model.nutrientsInformation.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
public class NutrientsInformation extends BaseEntity {

    @OneToOne(mappedBy = "nutrientsInformation")
    private Ingredient ingredient;

    @OneToMany(mappedBy = "nutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<Mineral> minerals;

    @OneToMany(mappedBy = "nutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<Proximate> proximates;

    @OneToMany(mappedBy = "nutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<Vitamin> vitamins;

    @OneToMany(mappedBy = "nutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<Lipid> lipids;

    @OneToMany(mappedBy = "nutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<OtherNutrient> otherNutrients;


    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Collection<Mineral> getMinerals() {
        return minerals;
    }

    public void setMinerals(Collection<Mineral> minerals) {
        this.minerals = minerals;
    }

    public Collection<Proximate> getProximates() {
        return proximates;
    }

    public void setProximates(Collection<Proximate> proximates) {
        this.proximates = proximates;
    }

    public Collection<Vitamin> getVitamins() {
        return vitamins;
    }

    public void setVitamins(Collection<Vitamin> vitamins) {
        this.vitamins = vitamins;
    }

    public Collection<Lipid> getLipids() {
        return lipids;
    }

    public void setLipids(Collection<Lipid> lipids) {
        this.lipids = lipids;
    }

    public Collection<gr.foodNhealth.model.nutrientsInformation.OtherNutrient> getOtherNutrients() {
        return otherNutrients;
    }

    public void setOtherNutrients(Collection<gr.foodNhealth.model.nutrientsInformation.OtherNutrient> OtherNutrient) {
        this.otherNutrients = OtherNutrient;
    }
}
