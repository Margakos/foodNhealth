package gr.foodNhealth.service;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class IngredientService {

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
    public Ingredient initNewIngredient (Ingredient ingredient) {
        NutrientsInformation nutrientsInfo = new NutrientsInformation();
        nutrientsInfo.setDeleted(false);
        nutrientsInfo.setIsActive(true);
        nutrientsInfo = nutrientsInformationRepository.save(nutrientsInfo);

        lipidService.initIngredientLipids(nutrientsInfo);
        proximateService.initIngredientProximates(nutrientsInfo);
        mineralService.initIngredientMinerals(nutrientsInfo);
        vitaminService.initIngredientVitamins(nutrientsInfo);
        otherNutrientService.initIngredientOtherNutrients(nutrientsInfo);

        ingredient.setNutrientsInformation(nutrientsInfo);
        return ingredient;
    }
}
