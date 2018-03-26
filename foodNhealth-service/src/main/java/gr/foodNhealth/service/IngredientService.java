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

    @Transactional
    public Ingredient initNewIngredient (Ingredient ingredient) {
        NutrientsInformation nutrientsInfo = new NutrientsInformation();
        nutrientsInfo.setLipids(new ArrayList<>());
        nutrientsInfo.setMinerals(new ArrayList<>());
        nutrientsInfo.setOtherNutrients(new ArrayList<>());
        nutrientsInfo.setProximates(new ArrayList<>());
        nutrientsInfo.setDeleted(false);
        nutrientsInfo.setIsActive(true);
        nutrientsInformationRepository.save(nutrientsInfo);

        ingredient.setNutrientsInformation(nutrientsInfo);
        return ingredient;
    }
}
