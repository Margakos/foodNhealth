package gr.foodNhealth.listener;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.IngredientRepository;
import gr.foodNhealth.repository.SelectedRecipeRepository;
import gr.foodNhealth.service.ProductService;
import gr.foodNhealth.service.SelectedRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler({SelectedRecipe.class})
public class SelectedRecipeEventHandler {

    @Autowired
    private SelectedRecipeRepository selectedRecipeRepository;

    @Autowired
    private SelectedRecipeService selectedRecipeService;

    @HandleBeforeCreate
    public void handleBeforeCreate (SelectedRecipe selectedRecipe) {
        selectedRecipe = selectedRecipeService.initNewSelectedRecipe(selectedRecipe);
    }
}
