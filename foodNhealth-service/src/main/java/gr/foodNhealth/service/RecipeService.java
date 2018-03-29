package gr.foodNhealth.service;

import gr.foodNhealth.model.*;
import gr.foodNhealth.model.Recipe;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class RecipeService {

    @Autowired
    private UtilsService utils;

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

    @Transactional
    public Recipe initNewRecipe (Recipe recipe, Collection<Product> products) {
        NutrientsInformation nutrientsInformation = new NutrientsInformation();
        nutrientsInformation.setTitle("Recipe");
        nutrientsInformation.setDeleted(false);
        nutrientsInformation.setIsActive(true);
        nutrientsInformation = nutrientsInformationRepository.save(nutrientsInformation);

        nutrientsInformation.setLipids(lipidService.initRecipeLipids(nutrientsInformation));
        nutrientsInformation.setProximates(proximateService.initRecipeProximates(nutrientsInformation));
        nutrientsInformation.setMinerals(mineralService.initRecipeMinerals(nutrientsInformation));
        nutrientsInformation.setVitamins(vitaminService.initRecipeVitamins(nutrientsInformation));
        nutrientsInformation.setOtherNutrients(otherNutrientService.initRecipeOtherNutrients(nutrientsInformation));
        nutrientsInformation = utils.propagateNutrientsInformationToRecipe(nutrientsInformation, products);
//        nutrientsInformationRepository.save(nutrientsInformation);

        recipe.setNutrientsInformation(nutrientsInformation);
        return recipe;
    }
}
