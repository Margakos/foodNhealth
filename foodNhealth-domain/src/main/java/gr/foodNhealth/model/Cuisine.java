package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Cuisine extends BaseEntity {

    @ManyToMany(mappedBy = "cuisines")
    private Collection<Recipe> recipes;

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }
}
