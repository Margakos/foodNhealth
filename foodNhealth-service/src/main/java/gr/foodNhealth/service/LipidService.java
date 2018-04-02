package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.Lipid;
import gr.foodNhealth.repository.LipidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LipidService {

    @Autowired
    private LipidRepository lipidRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    private Collection<Lipid> initLipids (NutrientsInformation nutrientsInfo) {
        List<Lipid> lipids = new ArrayList<>();
        lipids.add(new Lipid("Saturated", nutrientsInfo));
        lipids.add(new Lipid("Monounsaturated", nutrientsInfo));
        lipids.add(new Lipid("Polyunsaturated", nutrientsInfo));
        lipids.add(new Lipid("Trans", nutrientsInfo));
        lipids.forEach(lipid -> publisher.publishEvent(new BeforeCreateEvent(lipid)));
        return lipids;
    }

    public Collection<Lipid> initIngredientLipids (NutrientsInformation nutrientsInfo) {
        Collection<Lipid> lipids = initLipids(nutrientsInfo);
        lipidRepository.save(lipids);
        return lipids;
    }

    public Collection<Lipid> initProductLipids (NutrientsInformation nutrientsInformation) {
        Collection<Lipid> productLipids = initLipids(nutrientsInformation);
        lipidRepository.save(productLipids);
        return productLipids;
    }
}
