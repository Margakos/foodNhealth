package gr.foodNhealth.model;

import gr.foodNhealth.model.nutrientsInformation.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class NutrientsInformation extends BaseEntity {

    @OneToOne(mappedBy = "nutrientsInformation")
    private Ingredient ingredient;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Product product;

    @OneToOne(mappedBy = "nutrientsInformation")
    private Recipe recipe;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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

    public NutrientsInformation add (NutrientsInformation nutrientsInformation) {
        Map<String, BigDecimal> sourceMineralMap = new HashMap<>();
        nutrientsInformation.getMinerals().forEach(mineral -> sourceMineralMap.put(mineral.getMineralType(), mineral.getQuantity()));
        Map<String, BigDecimal> sourceProximateMap = new HashMap<>();
        nutrientsInformation.getProximates().forEach(proximate -> sourceProximateMap.put(proximate.getProximateType(), proximate.getQuantity()));
        Map<String, BigDecimal> sourceLipidMap = new HashMap<>();
        nutrientsInformation.getLipids().forEach(lipid -> sourceLipidMap.put(lipid.getLipidType(), lipid.getQuantity()));
        Map<String, BigDecimal> sourceVitaminMap = new HashMap<>();
        nutrientsInformation.getVitamins().forEach(vitamin -> sourceVitaminMap.put(vitamin.getVitaminType(), vitamin.getQuantity()));
        Map<String, BigDecimal> sourceOtherNutrientMap = new HashMap<>();
        nutrientsInformation.getOtherNutrients().forEach(otherNutrient -> sourceOtherNutrientMap.put(otherNutrient.getOtherNutrientType(), otherNutrient.getQuantity()));

        this.getMinerals().forEach(targetMineral -> {
            if (sourceMineralMap.containsKey(targetMineral.getMineralType())) {
                targetMineral.setQuantity(targetMineral.getQuantity().add(sourceMineralMap.get(targetMineral.getMineralType())));
            }
        });
        this.getProximates().forEach(targetProximate -> {
            if (sourceProximateMap.containsKey(targetProximate.getProximateType())) {
                targetProximate.setQuantity(targetProximate.getQuantity().add(sourceProximateMap.get(targetProximate.getProximateType())));
            }
        });
        this.getLipids().forEach(targetLipid -> {
            if (sourceLipidMap.containsKey(targetLipid.getLipidType())) {
                targetLipid.setQuantity(targetLipid.getQuantity().add(sourceLipidMap.get(targetLipid.getLipidType())));
            }
        });
        this.getVitamins().forEach(targetVitamin -> {
            if (sourceVitaminMap.containsKey(targetVitamin.getVitaminType())) {
                targetVitamin.setQuantity(targetVitamin.getQuantity().add(sourceVitaminMap.get(targetVitamin.getVitaminType())));
            }
        });
        this.getOtherNutrients().forEach(targetOtherNutrient -> {
            if (sourceOtherNutrientMap.containsKey(targetOtherNutrient.getOtherNutrientType())) {
                targetOtherNutrient.setQuantity(targetOtherNutrient.getQuantity().add(sourceOtherNutrientMap.get(targetOtherNutrient.getOtherNutrientType())));
            }
        });

        return this;
    }
}
