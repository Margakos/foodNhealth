package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.repository.OtherNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OtherNutrientService {

    @Autowired
    private OtherNutrientRepository otherNutrientRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    private Collection<OtherNutrient> initOtherNutrients (NutrientsInformation nutrientsInformation) {
        List<OtherNutrient> otherNutrients = new ArrayList<>();
        otherNutrients.add(new OtherNutrient("Alcohol", nutrientsInformation));
        otherNutrients.add(new OtherNutrient("Caffeine", nutrientsInformation));
        otherNutrients.add(new OtherNutrient("Gluten", nutrientsInformation));
        otherNutrients.add(new OtherNutrient("Lactose", nutrientsInformation));
        otherNutrients.forEach(otherNutrient ->  publisher.publishEvent(new BeforeCreateEvent(otherNutrient)));
        return otherNutrients;
    }

    public Collection<OtherNutrient> initIngredientOtherNutrients (NutrientsInformation nutrientsInformation) {
        Collection<OtherNutrient> otherNutrients = initOtherNutrients(nutrientsInformation);
        otherNutrientRepository.save(otherNutrients);
        return otherNutrients;
    }

    public Collection<OtherNutrient> initProductOtherNutrients (NutrientsInformation nutrientsInformation) {
        Collection<OtherNutrient> productOtherNutrients = initOtherNutrients(nutrientsInformation);
        otherNutrientRepository.save(productOtherNutrients);
        return productOtherNutrients;
    }
}
