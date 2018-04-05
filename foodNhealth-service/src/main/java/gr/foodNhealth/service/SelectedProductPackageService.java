package gr.foodNhealth.service;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.IngredientPortionRepository;
import gr.foodNhealth.repository.SelectedProductPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.data.rest.core.event.BeforeSaveEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class SelectedProductPackageService {

    @Autowired
    private IngredientPortionRepository ingredientPortionRepository;

    @Autowired
    private SelectedProductPackageRepository selectedProductPackageRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UtilsService utils;

    @Transactional
    public void linkSelectedProductPackages (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) {
        selectedProductPackages.forEach(selectedProductPackage -> {
            selectedProductPackage.setSelectedRecipe(selectedRecipe);
            publisher.publishEvent(new BeforeCreateEvent(selectedProductPackage));
        });
        selectedProductPackages = selectedProductPackageRepository.save(selectedProductPackages);
        // Find quantities
        selectedProductPackages.forEach(selectedProductPackage -> {
            IngredientPortion ingredientPortion = ingredientPortionRepository.findBySelectedProductPackage(selectedProductPackage);
            BigDecimal quantity = utils.getQuantity(selectedProductPackage, ingredientPortion);
            selectedProductPackage.setQuantity(quantity);
        });
        selectedProductPackageRepository.save(selectedProductPackages);
    }

    @Transactional
    public void updateSelectedProductPackages (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) {
        selectedProductPackages.forEach(selectedProductPackage -> {
            IngredientPortion ingredientPortion = ingredientPortionRepository.findBySelectedProductPackage(selectedProductPackage);
            BigDecimal quantity = utils.getQuantity(selectedProductPackage, ingredientPortion);
            selectedProductPackage.setQuantity(quantity);
            publisher.publishEvent(new BeforeSaveEvent(selectedProductPackage));
        });
        selectedProductPackageRepository.save(selectedProductPackages);
    }
}
