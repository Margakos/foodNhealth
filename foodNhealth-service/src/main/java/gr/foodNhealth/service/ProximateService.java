package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.model.nutrientsInformation.Proximate;
import gr.foodNhealth.repository.ProximateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProximateService {

    @Autowired
    private ProximateRepository proximateRepository;

    public Collection<Proximate> initIngredientProximates (NutrientsInformation nutrientsInformation) {
        List<Proximate> proximates = new ArrayList<>();
        proximates.add(new Proximate("Water", nutrientsInformation));
        proximates.add(new Proximate("Energy", nutrientsInformation));
        proximates.add(new Proximate("Protein", nutrientsInformation));
        proximates.add(new Proximate("Fat", nutrientsInformation));
        proximates.add(new Proximate("Carbohydrate", nutrientsInformation));
        proximates.add(new Proximate("Sugars", nutrientsInformation));
        proximates.add(new Proximate("Fibers", nutrientsInformation));
        proximates.forEach(proximate -> {
            proximate.setIsActive(true);
            proximate.setDeleted(false);
        });
        proximateRepository.save(proximates);
        return proximates;
    }
}
