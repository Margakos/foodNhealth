package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.OtherNutrient;
import gr.foodNhealth.model.nutrientsInformation.Proximate;
import gr.foodNhealth.repository.ProximateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProximateService {

    @Autowired
    private ProximateRepository proximateRepository;

    private Collection<Proximate> initProximates (NutrientsInformation nutrientsInformation) {
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
        return proximates;
    }

    public Collection<Proximate> initIngredientProximates (NutrientsInformation nutrientsInformation) {
        Collection<Proximate> proximates = initProximates(nutrientsInformation);
        proximateRepository.save(proximates);
        return proximates;
    }

    public Collection<Proximate> initProductProximates (NutrientsInformation nutrientsInformation) {
        Collection<Proximate> productProximates = initProximates(nutrientsInformation);
        proximateRepository.save(productProximates);
        return productProximates;
    }

    public Collection<Proximate> initRecipeProximates (NutrientsInformation nutrientsInformation) {
        Collection<Proximate> recipeProximates = initProximates(nutrientsInformation);
        proximateRepository.save(recipeProximates);
        return recipeProximates;
    }
}
