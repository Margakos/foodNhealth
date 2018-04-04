package gr.foodNhealth.service;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.IngredientPortionRepository;
import gr.foodNhealth.repository.SelectedProductPackageRepository;
import gr.foodNhealth.repository.SelectedRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SelectedRecipeService {

    @Autowired
    private SelectedProductPackageRepository selectedProductPackageRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public void linkSelectedProductPackages (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) {
        selectedProductPackages.forEach(selectedProductPackage -> {
            selectedProductPackage.setSelectedRecipe(selectedRecipe);
            publisher.publishEvent(new BeforeCreateEvent(selectedProductPackage));
        });
        selectedProductPackages = selectedProductPackageRepository.save(selectedProductPackages);

        selectedRecipe.setSelectedProductPackages(selectedProductPackages);
    }
}
