package gr.foodNhealth.controller;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.RecipeRepository;
import gr.foodNhealth.repository.SelectedRecipeRepository;
import gr.foodNhealth.service.RecipeService;
import gr.foodNhealth.service.SelectedRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@RepositoryRestController
public class SelectedProductPackageOverrideController {

    @Autowired
    private SelectedRecipeRepository selectedRecipeRepository;

    @Autowired
    private SelectedRecipeService selectedRecipeService;

    @PostMapping(value = "selectedRecipes/{id}/selectedProductPackages")
    public ResponseEntity<?> saveIngredientPortions(@PathVariable("id") Long selectedRecipeId,
                                                    @RequestBody Collection<SelectedProductPackage> selectedProductPackages) {
        SelectedRecipe selectedRecipe = selectedRecipeRepository.findOne(selectedRecipeId);
        try {
            selectedRecipeService.linkSelectedProductPackages(selectedProductPackages, selectedRecipe);
        } catch (Exception e) {
            e.printStackTrace();
            selectedRecipeRepository.delete(selectedRecipeId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(null);
    }
}
