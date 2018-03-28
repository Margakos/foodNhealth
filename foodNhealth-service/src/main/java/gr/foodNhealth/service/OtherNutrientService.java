package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.repository.OtherNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OtherNutrientService {

    @Autowired
    private OtherNutrientRepository otherNutrientRepository;

    public Collection<OtherNutrient> initIngredientOtherNutrients (NutrientsInformation nutrientsInformation) {
        List<OtherNutrient> otherNutrients = new ArrayList<>();
        otherNutrients.add(new OtherNutrient("Alcohol", nutrientsInformation));
        otherNutrients.add(new OtherNutrient("Caffeine", nutrientsInformation));
        otherNutrients.forEach(otherNutrient -> {
            otherNutrient.setIsActive(true);
            otherNutrient.setDeleted(false);
        });
        otherNutrientRepository.save(otherNutrients);
        return otherNutrients;
    }
}
