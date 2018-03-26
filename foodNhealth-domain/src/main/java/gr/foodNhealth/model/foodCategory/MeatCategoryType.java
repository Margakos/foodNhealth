package gr.foodNhealth.model.foodCategory;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.Ingredient;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class MeatCategoryType extends BaseEntity {

    @OneToMany(mappedBy = "meatCategoryType")
    private Collection<Ingredient> ingredients;

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
