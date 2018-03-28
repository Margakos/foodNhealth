package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.Vitamin;
import gr.foodNhealth.repository.VitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VitaminService {

    @Autowired
    private VitaminRepository vitaminRepository;

    public Collection<Vitamin> initIngredientVitamins (NutrientsInformation nutrientsInformation) {
        List<Vitamin> vitamins = new ArrayList<>();
        vitamins.add(new Vitamin("Vitamin A", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B1", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B2", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B3", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B5", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B6", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B7", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B9", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin B12", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin C", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin D", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin E", nutrientsInformation));
        vitamins.add(new Vitamin("Vitamin K", nutrientsInformation));
        vitamins.add(new Vitamin("Choline, nutrientsInformation", nutrientsInformation));
        vitamins.add(new Vitamin("Carotenoids", nutrientsInformation));
        vitamins.forEach(vitamin -> {
            vitamin.setIsActive(true);
            vitamin.setDeleted(false);
        });
        vitaminRepository.save(vitamins);
        return vitamins;
    }
}
