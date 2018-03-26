package gr.foodNhealth.model.foodCategory;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.Ingredient;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class FoodCategoryCoreType extends BaseEntity {

    @OneToMany(mappedBy = "foodCategoryCoreType")
    private Collection<Ingredient> ingredients;

    @OneToMany(mappedBy = "foodCategoryCoreType")
    private Collection<FoodCategorySubType> foodCategorySubTypes;

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Collection<FoodCategorySubType> getFoodCategorySubTypes() {
        return foodCategorySubTypes;
    }

    public void setFoodCategorySubTypes(Collection<FoodCategorySubType> foodCategorySubTypes) {
        this.foodCategorySubTypes = foodCategorySubTypes;
    }
}
