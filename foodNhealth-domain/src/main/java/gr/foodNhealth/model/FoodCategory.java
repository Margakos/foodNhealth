package gr.foodNhealth.model;

import gr.foodNhealth.model.foodCategory.FoodCategoryMainType;
import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import gr.foodNhealth.model.foodCategory.MeatCategoryType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class FoodCategory extends BaseEntity {

    @NotNull
    @OneToOne
    private Ingredient ingredient;
    
    @NotNull
    @ManyToOne
    private FoodCategoryMainType foodCategoryMainType;

    @NotNull
    @ManyToOne
    private FoodCategorySubType foodCategorySubType;
    
    @ManyToOne
    private MeatCategoryType meatCategoryType;


    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public FoodCategoryMainType getFoodCategoryMainType() {
        return foodCategoryMainType;
    }

    public void setFoodCategoryMainType(FoodCategoryMainType foodCategoryMainType) {
        this.foodCategoryMainType = foodCategoryMainType;
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
}
