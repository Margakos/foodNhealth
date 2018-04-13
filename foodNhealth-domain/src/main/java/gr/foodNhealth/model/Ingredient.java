package gr.foodNhealth.model;

import gr.foodNhealth.model.foodCategory.FoodCategoryCoreType;
import gr.foodNhealth.model.foodCategory.FoodCategorySubType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Ingredient extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @Basic
    @Column
    private String photoPath;

    @Basic
    @NotNull
    @Column(nullable = false)
    private Boolean quantified;

    @ManyToOne(optional = false)
    private FoodCategoryCoreType foodCategoryCoreType;

    @ManyToOne(optional = false)
    private FoodCategorySubType foodCategorySubType;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;

    @OneToMany(mappedBy = "ingredient")
    private Collection<Product> products;

    @OneToMany(mappedBy = "ingredient")
    private Collection<IngredientPortion> ingredientPortions;

    @ManyToMany
    private Collection<Allergy> allergies;

    @ManyToMany(mappedBy = "dislikedIngredients")
    private Collection<Preference> preferences;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Boolean getQuantified() {
        return quantified;
    }

    public void setQuantified(Boolean chopped) {
        this.quantified = chopped;
    }

    public FoodCategoryCoreType getFoodCategoryCoreType() {
        return foodCategoryCoreType;
    }

    public void setFoodCategoryCoreType(FoodCategoryCoreType foodCategoryCoreType) {
        this.foodCategoryCoreType = foodCategoryCoreType;
    }

    public FoodCategorySubType getFoodCategorySubType() {
        return foodCategorySubType;
    }

    public void setFoodCategorySubType(FoodCategorySubType foodCategorySubType) {
        this.foodCategorySubType = foodCategorySubType;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Collection<IngredientPortion> getIngredientPortions() {
        return ingredientPortions;
    }

    public void setIngredientPortions(Collection<IngredientPortion> ingredientPortions) {
        this.ingredientPortions = ingredientPortions;
    }

    public Collection<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Collection<Allergy> allergies) {
        this.allergies = allergies;
    }

    public Collection<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Collection<Preference> preferences) {
        this.preferences = preferences;
    }
}
