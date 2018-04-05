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
    private SelectedRecipe selectedRecipe;

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

    public SelectedRecipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(SelectedRecipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
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

    /**
     * @param nutrientsInformation The NutrientsInformations to be added
     * @param sourceQuantity The quantity of entity containing NutrientsInformation passed as argument
     * @param targetQuantity The quantity of entity containing NutrientsInformation that called add method
     * @return The NutrientsInformation as percentage sum of two NutrientsInformation
     */
    public NutrientsInformation add (NutrientsInformation nutrientsInformation, BigDecimal sourceQuantity, BigDecimal targetQuantity) {
        final BigDecimal _sourceQuantity_ = sourceQuantity.divide(new BigDecimal(100), 12, BigDecimal.ROUND_HALF_UP);
        final BigDecimal _targetQuantity_ = targetQuantity.divide(new BigDecimal(100), 12, BigDecimal.ROUND_HALF_UP);

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
                BigDecimal sourcePercentageQuantity = sourceMineralMap.get(targetMineral.getMineralType()).multiply(_sourceQuantity_);
                BigDecimal targetPercentageQuantity = targetMineral.getQuantity().multiply(_targetQuantity_);
                BigDecimal sum = sourcePercentageQuantity.add(targetPercentageQuantity);
                sum = sum.divide(_sourceQuantity_.add(_targetQuantity_), 12, BigDecimal.ROUND_HALF_UP);
                targetMineral.setQuantity(sum);
            }
        });
        this.getProximates().forEach(targetProximate -> {
            if (sourceProximateMap.containsKey(targetProximate.getProximateType())) {
                BigDecimal sourcePercentageQuantity = sourceProximateMap.get(targetProximate.getProximateType()).multiply(_sourceQuantity_);
                BigDecimal targetPercentageQuantity = targetProximate.getQuantity().multiply(_targetQuantity_);
                BigDecimal sum = sourcePercentageQuantity.add(targetPercentageQuantity);
                sum = sum.divide(_sourceQuantity_.add(_targetQuantity_), 12, BigDecimal.ROUND_HALF_UP);
                targetProximate.setQuantity(sum);
            }
        });
        this.getLipids().forEach(targetLipid -> {
            if (sourceLipidMap.containsKey(targetLipid.getLipidType())) {
                BigDecimal sourcePercentageQuantity = sourceLipidMap.get(targetLipid.getLipidType()).multiply(_sourceQuantity_);
                BigDecimal targetPercentageQuantity = targetLipid.getQuantity().multiply(_targetQuantity_);
                BigDecimal sum = sourcePercentageQuantity.add(targetPercentageQuantity);
                sum = sum.divide(_sourceQuantity_.add(_targetQuantity_), 12, BigDecimal.ROUND_HALF_UP);
                targetLipid.setQuantity(sum);
            }
        });
        this.getVitamins().forEach(targetVitamin -> {
            if (sourceVitaminMap.containsKey(targetVitamin.getVitaminType())) {
                BigDecimal sourcePercentageQuantity = sourceVitaminMap.get(targetVitamin.getVitaminType()).multiply(_sourceQuantity_);
                BigDecimal targetPercentageQuantity = targetVitamin.getQuantity().multiply(_targetQuantity_);
                BigDecimal sum = sourcePercentageQuantity.add(targetPercentageQuantity);
                sum = sum.divide(_sourceQuantity_.add(_targetQuantity_), 12, BigDecimal.ROUND_HALF_UP);
                targetVitamin.setQuantity(sum);
            }
        });
        this.getOtherNutrients().forEach(targetOtherNutrient -> {
            if (sourceOtherNutrientMap.containsKey(targetOtherNutrient.getOtherNutrientType())) {
                BigDecimal sourcePercentageQuantity = sourceOtherNutrientMap.get(targetOtherNutrient.getOtherNutrientType()).multiply(_sourceQuantity_);
                BigDecimal targetPercentageQuantity = targetOtherNutrient.getQuantity().multiply(_targetQuantity_);
                BigDecimal sum = sourcePercentageQuantity.add(targetPercentageQuantity);
                sum = sum.divide(_sourceQuantity_.add(_targetQuantity_), 12, BigDecimal.ROUND_HALF_UP);
                targetOtherNutrient.setQuantity(sum);
            }
        });

        return this;
    }
}
