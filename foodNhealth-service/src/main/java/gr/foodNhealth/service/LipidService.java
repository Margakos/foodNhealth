package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.Lipid;
import gr.foodNhealth.repository.LipidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LipidService {

    @Autowired
    private LipidRepository lipidRepository;

    public Collection<Lipid> initIngredientLipids (NutrientsInformation nutrientsInfo) {
        List<Lipid> lipids = new ArrayList<>();
        lipids.add(new Lipid("Saturated", nutrientsInfo));
        lipids.add(new Lipid("Monounsaturated", nutrientsInfo));
        lipids.add(new Lipid("Polyunsaturated", nutrientsInfo));
        lipids.add(new Lipid("Trans", nutrientsInfo));
        lipids.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        lipidRepository.save(lipids);
        return lipids;
    }
}
