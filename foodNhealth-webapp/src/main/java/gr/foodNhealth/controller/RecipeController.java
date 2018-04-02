package gr.foodNhealth.controller;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.repository.RecipeRepository;
import gr.foodNhealth.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@BasePathAwareController
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "{id}/saveIngredientPortions")
    public ResponseEntity<?> saveIngredientPortions(@PathVariable("id") Long recipeId,
                                                    @RequestBody Collection<IngredientPortion> ingredientPortions) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        try {
            recipeService.linkIngredientPortion(ingredientPortions, recipe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(null);
    }
}
