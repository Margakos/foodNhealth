package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.Mineral;
import gr.foodNhealth.repository.MineralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MineralService {
    
    @Autowired
    private MineralRepository mineralRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    private Collection<Mineral> initMinerals (NutrientsInformation nutrientsInformation) {
        List<Mineral> minerals = new ArrayList<>();
        minerals.add(new Mineral("Calcium", nutrientsInformation));
        minerals.add(new Mineral("Chromium", nutrientsInformation));
        minerals.add(new Mineral("Copper", nutrientsInformation));
        minerals.add(new Mineral("Fluoride", nutrientsInformation));
        minerals.add(new Mineral("Iodine", nutrientsInformation));
        minerals.add(new Mineral("Iron", nutrientsInformation));
        minerals.add(new Mineral("Magnesium", nutrientsInformation));
        minerals.add(new Mineral("Manganese", nutrientsInformation));
        minerals.add(new Mineral("Molybdenum", nutrientsInformation));
        minerals.add(new Mineral("Phosphorus", nutrientsInformation));
        minerals.add(new Mineral("Selenium", nutrientsInformation));
        minerals.add(new Mineral("Zinc", nutrientsInformation));
        minerals.add(new Mineral("Potassium", nutrientsInformation));
        minerals.add(new Mineral("Sodium", nutrientsInformation));
        minerals.add(new Mineral("Chloride", nutrientsInformation));
        minerals.forEach(mineral -> publisher.publishEvent(new BeforeCreateEvent(mineral)));
        return minerals;
    }

    public Collection<Mineral> initIngredientMinerals (NutrientsInformation nutrientsInformation) {
        Collection<Mineral> minerals = initMinerals(nutrientsInformation);
        mineralRepository.save(minerals);
        return minerals;
    }

    public Collection<Mineral> initProductMinerals (NutrientsInformation nutrientsInformation) {
        Collection<Mineral> productMinerals = initMinerals(nutrientsInformation);
        mineralRepository.save(productMinerals);
        return productMinerals;
    }

    public Collection<Mineral> initSelectedRecipeMinerals (NutrientsInformation nutrientsInformation) {
        Collection<Mineral> selectedRecipeMinerals = initMinerals(nutrientsInformation);
        mineralRepository.save(selectedRecipeMinerals);
        return selectedRecipeMinerals;
    }
}
