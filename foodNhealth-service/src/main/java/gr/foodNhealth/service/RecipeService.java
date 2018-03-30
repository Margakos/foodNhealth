package gr.foodNhealth.service;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.repository.IngredientPortionRepository;
import gr.foodNhealth.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecipeService {

    @Autowired
    private IngredientPortionRepository ingredientPortionRepository;

    public void linkIngredientPortion (Collection<IngredientPortion> ingredientPortions, Recipe recipe) {
        ingredientPortions.forEach(ingredientPortion -> {
            ingredientPortion.setRecipe(recipe);
            ingredientPortion.setIsActive(true);
            ingredientPortion.setDeleted(false);
        });
        ingredientPortions = ingredientPortionRepository.save(ingredientPortions);

        recipe.setIngredientPortions(ingredientPortions);
    }
}
