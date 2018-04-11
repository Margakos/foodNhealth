package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Allergy extends BaseEntity {

    @ManyToMany(mappedBy = "allergies")
    private Collection<Ingredient> ingredients;


    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
