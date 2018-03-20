package gr.foodNhealth.model;

import gr.foodNhealth.model.foodCategoty.FoodCategoryParentType;
import gr.foodNhealth.model.foodCategoty.FoodCategoryType;
import gr.foodNhealth.model.foodCategoty.MeatCategoryType;

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
    private FoodCategoryParentType foodCategoryParentType;

    @NotNull
    @ManyToOne
    private FoodCategoryType foodCategoryType;
    
    @ManyToOne
    private MeatCategoryType meatCategoryType;


    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public FoodCategoryParentType getFoodCategoryParentType() {
        return foodCategoryParentType;
    }

    public void setFoodCategoryParentType(FoodCategoryParentType foodCategoryParentType) {
        this.foodCategoryParentType = foodCategoryParentType;
    }

    public FoodCategoryType getFoodCategoryType() {
        return foodCategoryType;
    }

    public void setFoodCategoryType(FoodCategoryType foodCategoryType) {
        this.foodCategoryType = foodCategoryType;
    }

    public MeatCategoryType getMeatCategoryType() {
        return meatCategoryType;
    }

    public void setMeatCategoryType(MeatCategoryType meatCategoryType) {
        this.meatCategoryType = meatCategoryType;
    }
}
