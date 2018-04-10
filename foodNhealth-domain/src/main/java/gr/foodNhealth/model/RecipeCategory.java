package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class RecipeCategory extends BaseEntity {

    @OneToMany(mappedBy = "recipeCategory")
    private Collection<Recipe> recipes;

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }
}
