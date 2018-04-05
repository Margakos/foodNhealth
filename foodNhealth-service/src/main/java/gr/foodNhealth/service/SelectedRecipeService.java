package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.model.SelectedRecipe;
import gr.foodNhealth.repository.IngredientPortionRepository;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import gr.foodNhealth.repository.SelectedProductPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SelectedRecipeService {

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

    @Transactional
    public SelectedRecipe initNewSelectedRecipe (SelectedRecipe selectedRecipe) {
        NutrientsInformation nutrientsInformation = new NutrientsInformation();
        nutrientsInformation.setTitle("SelectedRecipe");
        publisher.publishEvent(new BeforeCreateEvent(nutrientsInformation));
        nutrientsInformation = nutrientsInformationRepository.save(nutrientsInformation);

        nutrientsInformation.setLipids(lipidService.initSelectedRecipeLipids(nutrientsInformation));
        nutrientsInformation.setProximates(proximateService.initSelectedRecipeProximates(nutrientsInformation));
        nutrientsInformation.setMinerals(mineralService.initSelectedRecipeMinerals(nutrientsInformation));
        nutrientsInformation.setVitamins(vitaminService.initSelectedRecipeVitamins(nutrientsInformation));
        nutrientsInformation.setOtherNutrients(otherNutrientService.initSelectedRecipeOtherNutrients(nutrientsInformation));

        selectedRecipe.setNutrientsInformation(nutrientsInformation);
        return selectedRecipe;
    }

    @Transactional
    public void propagateNutrientsInformation (Collection<SelectedProductPackage> selectedProductPackages, SelectedRecipe selectedRecipe) {
        NutrientsInformation nutrientsInformation = selectedRecipe.getNutrientsInformation();
        nutrientsInformation = utils.propagateNutrientsInformationToSelectedRecipe(nutrientsInformation, selectedProductPackages);
        nutrientsInformationRepository.save(nutrientsInformation);
    }
}
