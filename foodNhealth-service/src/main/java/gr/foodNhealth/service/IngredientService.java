package gr.foodNhealth.service;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
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

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    public Ingredient initNewIngredient (Ingredient ingredient) {
        NutrientsInformation nutrientsInformation = new NutrientsInformation();
        nutrientsInformation.setTitle("Ingredient");
        publisher.publishEvent(new BeforeCreateEvent(nutrientsInformation));
        nutrientsInformation = nutrientsInformationRepository.save(nutrientsInformation);

        lipidService.initIngredientLipids(nutrientsInformation);
        proximateService.initIngredientProximates(nutrientsInformation);
        mineralService.initIngredientMinerals(nutrientsInformation);
        vitaminService.initIngredientVitamins(nutrientsInformation);
        otherNutrientService.initIngredientOtherNutrients(nutrientsInformation);

        ingredient.setNutrientsInformation(nutrientsInformation);
        return ingredient;
    }
}
