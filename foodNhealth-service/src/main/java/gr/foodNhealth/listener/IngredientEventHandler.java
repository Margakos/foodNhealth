package gr.foodNhealth.listener;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.repository.IngredientRepository;
import gr.foodNhealth.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Ingredient.class)
public class IngredientEventHandler {

    @Autowired
    private IngredientService ingredientService;

    @HandleBeforeCreate
    public void handleAfterCreate (Ingredient ingredient) {
        ingredient = ingredientService.initNewIngredient(ingredient);
    }
}
