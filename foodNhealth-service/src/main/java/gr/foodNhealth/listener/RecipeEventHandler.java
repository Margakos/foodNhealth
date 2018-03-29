package gr.foodNhealth.listener;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.repository.IngredientRepository;
import gr.foodNhealth.service.ProductService;
import gr.foodNhealth.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RepositoryEventHandler(Recipe.class)
public class RecipeEventHandler {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeService recipeService;

    @HandleBeforeCreate
    public void handleBeforeCreate (Recipe recipe) {
        Collection<Product> products = recipe.getProducts();
        recipe = recipeService.initNewRecipe(recipe, products);
    }
}
