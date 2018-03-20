package gr.foodNhealth.model;

import gr.foodNhealth.model.nutrientsInformation.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
public class NutrientsInformation extends BaseEntity {

    @OneToOne
    private Ingredient ingredient;

    @OneToMany(mappedBy = "nutrientsInformation")
    private Collection<Mineral> minerals;

    @OneToMany(mappedBy = "nutrientsInformation")
    private Collection<Proximate> proximates;

    @OneToMany(mappedBy = "nutrientsInformation")
    private Collection<Vitamin> vitamins;

    @OneToMany(mappedBy = "nutrientsInformation")
    private Collection<Lipid> lipids;

    @OneToMany(mappedBy = "nutrientsInformation")
    private Collection<OtherNutrient> OtherNutrient;


    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Collection<Mineral> getMinerals() {
        return minerals;
    }

    public void setMineral(Collection<Mineral> minerals) {
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

    public Collection<gr.foodNhealth.model.nutrientsInformation.OtherNutrient> getOtherNutrient() {
        return OtherNutrient;
    }

    public void setOtherNutrient(Collection<gr.foodNhealth.model.nutrientsInformation.OtherNutrient> OtherNutrient) {
        this.OtherNutrient = OtherNutrient;
    }
}
