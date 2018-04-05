package gr.foodNhealth.service;

import gr.foodNhealth.model.*;
import gr.foodNhealth.repository.IngredientPortionRepository;
import gr.foodNhealth.repository.NutrientsInformationRepository;
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
    private SelectedRecipeRepository selectedRecipeRepository;

    @Autowired
    private IngredientPortionRepository ingredientPortionRepository;

    @Autowired
    private SelectedProductPackageRepository selectedProductPackageRepository;

    @Autowired
    private NutrientsInformationRepository nutrientsInformationRepository;

    @Autowired
    private ProximateService proximateService;

    @Autowired
    private MineralService mineralService;

    @Autowired
    private VitaminService vitaminService;

    @Autowired
    private LipidService lipidService;

    @Autowired
    private OtherNutrientService otherNutrientService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UtilsService utils;

    public void linkSelectedProductPackages (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) {
        selectedProductPackages.forEach(selectedProductPackage -> {

            selectedProductPackage.setSelectedRecipe(selectedRecipe);
            publisher.publishEvent(new BeforeCreateEvent(selectedProductPackage));
        });
        selectedProductPackages = selectedProductPackageRepository.save(selectedProductPackages);
        // Find quantities
        selectedProductPackages.forEach(selectedProductPackage -> {
            IngredientPortion ingredientPortion = ingredientPortionRepository.findBySelectedProductPackage(selectedProductPackage);
            selectedProductPackage.setQuantity(ingredientPortion.getQuantity());
        });
        selectedProductPackages = selectedProductPackageRepository.save(selectedProductPackages);
//        selectedRecipe.setSelectedProductPackages(selectedProductPackages);
    }

    public void propagateNutrientsInformation (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) throws Exception {
        NutrientsInformation nutrientsInformation = new NutrientsInformation();
        try {
            nutrientsInformation.setTitle("SelectedRecipe");
            publisher.publishEvent(new BeforeCreateEvent(nutrientsInformation));
            nutrientsInformation = nutrientsInformationRepository.save(nutrientsInformation);

            nutrientsInformation.setLipids(lipidService.initSelectedRecipeLipids(nutrientsInformation));
            nutrientsInformation.setProximates(proximateService.initSelectedRecipeProximates(nutrientsInformation));
            nutrientsInformation.setMinerals(mineralService.initSelectedRecipeMinerals(nutrientsInformation));
            nutrientsInformation.setVitamins(vitaminService.initSelectedRecipeVitamins(nutrientsInformation));
            nutrientsInformation.setOtherNutrients(otherNutrientService.initSelectedRecipeOtherNutrients(nutrientsInformation));
            nutrientsInformation = utils.propagateNutrientsInformationToSelectedRecipe(nutrientsInformation, selectedProductPackages);

            selectedRecipe.setNutrientsInformation(nutrientsInformation);
            selectedRecipeRepository.save(selectedRecipe);
        } catch (Exception e) {
            e.printStackTrace();
            nutrientsInformationRepository.delete(nutrientsInformation);
            throw new Exception();
        }
    }
}
