package gr.foodNhealth.model.foodCategory;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.Ingredient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class MeatCategoryType extends BaseEntity {

    @OneToMany(mappedBy = "meatCategoryType")
    private Collection<Ingredient> ingredients;

    @ManyToOne(optional = false)
    private FoodCategorySubType foodCategorySubType;

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public FoodCategorySubType getFoodCategorySubType() {
        return foodCategorySubType;
    }

    public void setFoodCategorySubType(FoodCategorySubType foodCategorySubType) {
        this.foodCategorySubType = foodCategorySubType;
    }
}
