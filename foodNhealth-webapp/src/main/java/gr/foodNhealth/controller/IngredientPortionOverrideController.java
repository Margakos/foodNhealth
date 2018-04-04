package gr.foodNhealth.controller;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.repository.RecipeRepository;
import gr.foodNhealth.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RepositoryRestController
public class IngredientPortionOverrideController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "recipes/{id}/ingredientPortions")
    public ResponseEntity<?> saveIngredientPortions(@PathVariable("id") Long recipeId,
                                                    @RequestBody Collection<IngredientPortion> ingredientPortions) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        try {
            recipeService.linkIngredientPortion(ingredientPortions, recipe);
        } catch (Exception e) {
            e.printStackTrace();
            recipeRepository.delete(recipeId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(null);
    }
}
