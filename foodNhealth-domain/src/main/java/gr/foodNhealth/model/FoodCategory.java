package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FoodCategory extends BaseEntity {

    @NotNull
    @OneToOne
    private Ingredient ingredient;

    @Basic
    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodCategoryParentType foodCategoryParentType;

    @Basic
    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodCategoryType foodCategoryType;

    @Basic
    @Enumerated(EnumType.STRING)
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

    public enum FoodCategoryParentType {
        DAIRY,
        VEGETABLE,
        FRUITS_N_JUICES,
        STARCH,
        MEAT,
        OILS
    }

    public enum FoodCategoryType {
        MILK_SKIMMED, MILK_SEMI_SKIMMED, MILK_FULL_FAT,
        VEGETABLES,
        FRUIT, JUICE,
        BREAD, CEREAL, RICE, LEGUME, SOUP, STARCHY_VEGETABLES, OTHER,
        LOW_FAT, MEDIUM_FAT, HIGH_FAT,
        SATURATED, MONOUNSATURATED, POLYUNSATURATED
    }

    public enum MeatCategoryType {
        L_MEAT, L_CHEESE, L_FISH, L_OTHER,
        M_MEAT, M_CHEESE, M_FISH, M_EGG, M_OTHER,
        H_MEAT, H_CHEESE, H_FISH, H_OTHER
    }
}
