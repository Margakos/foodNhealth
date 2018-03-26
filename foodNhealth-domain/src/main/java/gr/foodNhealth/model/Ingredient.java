package gr.foodNhealth.model;

import gr.foodNhealth.model.foodCategory.FoodCategoryCoreType;
import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import gr.foodNhealth.model.foodCategory.MeatCategoryType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Ingredient extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String photoPath;

    @ManyToOne(optional = false)
    private FoodCategoryCoreType foodCategoryCoreType;

    @ManyToOne(optional = false)
    private FoodCategorySubType foodCategorySubType;

    @ManyToOne
    private MeatCategoryType meatCategoryType;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;

    @OneToMany(mappedBy = "ingredient")
    private Collection<Product> products;


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

    public MeatCategoryType getMeatCategoryType() {
        return meatCategoryType;
    }

    public void setMeatCategoryType(MeatCategoryType meatCategoryType) {
        this.meatCategoryType = meatCategoryType;
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
}
