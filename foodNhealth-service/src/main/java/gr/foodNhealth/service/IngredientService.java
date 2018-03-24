package gr.foodNhealth.service;

import gr.foodNhealth.model.FoodCategory;
import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.foodCategory.FoodCategoryMainType;
import gr.foodNhealth.model.foodCategory.FoodCategorySubType;
import gr.foodNhealth.repository.FoodCategoryRepository;
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
    private FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public Ingredient initNewIngredient (Ingredient ingredient) {
        NutrientsInformation nutrientsInfo = new NutrientsInformation();
        nutrientsInfo.setLipids(new ArrayList<>());
        nutrientsInfo.setMineral(new ArrayList<>());
        nutrientsInfo.setOtherNutrient(new ArrayList<>());
        nutrientsInfo.setProximates(new ArrayList<>());
        nutrientsInfo.setVitamins(new ArrayList<>());
        nutrientsInfo.setDeleted(false);
        nutrientsInfo.setIsActive(true);
        nutrientsInformationRepository.save(nutrientsInfo);

        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setDeleted(false);
        foodCategory.setIsActive(true);
        foodCategoryRepository.save(foodCategory);

        ingredient.setNutrientsInformation(nutrientsInfo);
        ingredient.setFoodCategory(foodCategory);
        return ingredient;
    }
}
