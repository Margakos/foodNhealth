package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Allergy extends BaseEntity {

    @ManyToMany(mappedBy = "allergies")
    private Collection<Ingredient> ingredients;

    @ManyToMany
    private Collection<Preference> preferences;


    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Collection<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Collection<Preference> preferences) {
        this.preferences = preferences;
    }
}
