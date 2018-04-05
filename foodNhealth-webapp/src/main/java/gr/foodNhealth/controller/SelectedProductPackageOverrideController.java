package gr.foodNhealth.controller;

import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.SelectedRecipeRepository;
import gr.foodNhealth.service.SelectedProductPackageService;
import gr.foodNhealth.service.SelectedRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @Autowired
    private SelectedProductPackageService selectedProductPackageService;

    @PostMapping(value = "selectedRecipes/{id}/selectedProductPackages")
    public ResponseEntity<?> createSelectedProductPackages(@PathVariable("id") Long selectedRecipeId,
                                                           @RequestBody Collection<SelectedProductPackage> selectedProductPackages) {
        SelectedRecipe selectedRecipe = selectedRecipeRepository.findOne(selectedRecipeId);
        try {
            selectedProductPackageService.linkSelectedProductPackages(selectedProductPackages, selectedRecipe);
            selectedRecipeService.propagateNutrientsInformation(selectedProductPackages, selectedRecipe);
        } catch (Exception e) {
            e.printStackTrace();
            selectedRecipeRepository.delete(selectedRecipeId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(null);
    }

    @PatchMapping(value = "selectedRecipes/{id}/selectedProductPackages")
    public ResponseEntity<?> saveSelectedProductPackages(@PathVariable("id") Long selectedRecipeId,
                                                         @RequestBody Collection<SelectedProductPackage> selectedProductPackages) {
        SelectedRecipe selectedRecipe = selectedRecipeRepository.findOne(selectedRecipeId);
        selectedProductPackageService.updateSelectedProductPackages(selectedProductPackages, selectedRecipe);
        selectedRecipeService.propagateNutrientsInformation(selectedProductPackages, selectedRecipe);
        return ResponseEntity.ok(null);
    }
}
