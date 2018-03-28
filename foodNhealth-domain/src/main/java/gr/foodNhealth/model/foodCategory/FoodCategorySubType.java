package gr.foodNhealth.model.foodCategory;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.Ingredient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class FoodCategorySubType extends BaseEntity {

    @OneToMany(mappedBy = "foodCategorySubType")
    private Collection<Ingredient> ingredients;

    @ManyToOne(optional = false)
    private FoodCategoryCoreType foodCategoryCoreType;

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public FoodCategoryCoreType getFoodCategoryCoreType() {
        return foodCategoryCoreType;
    }

    public void setFoodCategoryCoreType(FoodCategoryCoreType foodCategoryCoreType) {
        this.foodCategoryCoreType = foodCategoryCoreType;
    }
}
