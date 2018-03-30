package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Recipe extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String photoPath;

    @Basic
    @Column
    private String instruction;

    @ManyToOne
    private RecipeCategory recipeCategory;

    @ManyToOne
    private Cuisine cuisine;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Collection<IngredientPortion> ingredientPortions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Collection<IngredientPortion> getIngredientPortions() {
        return ingredientPortions;
    }

    public void setIngredientPortions(Collection<IngredientPortion> ingredientPortions) {
        this.ingredientPortions = ingredientPortions;
    }
}
