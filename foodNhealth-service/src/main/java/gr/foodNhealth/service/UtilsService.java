package gr.foodNhealth.service;

import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UtilsService {
    
    public NutrientsInformation propagateNutrientsInformationToProduct (NutrientsInformation productNutrientsInformation,
                                                                        NutrientsInformation nutrientsInformation) {
        Map<String, BigDecimal> mineralMap = new HashMap<>();
        nutrientsInformation.getMinerals().forEach(mineral -> mineralMap.put(mineral.getMineralType(), mineral.getQuantity()));
        Map<String, BigDecimal> proximateMap = new HashMap<>();
        nutrientsInformation.getProximates().forEach(proximate -> proximateMap.put(proximate.getProximateType(), proximate.getQuantity()));
        Map<String, BigDecimal> lipidMap = new HashMap<>();
        nutrientsInformation.getLipids().forEach(lipid -> lipidMap.put(lipid.getLipidType(), lipid.getQuantity()));
        Map<String, BigDecimal> vitaminMap = new HashMap<>();
        nutrientsInformation.getVitamins().forEach(vitamin -> vitaminMap.put(vitamin.getVitaminType(), vitamin.getQuantity()));
        Map<String, BigDecimal> otherNutrientMap = new HashMap<>();
        nutrientsInformation.getOtherNutrients().forEach(otherNutrient -> otherNutrientMap.put(otherNutrient.getOtherNutrientType(), otherNutrient.getQuantity()));

        Collection<Mineral> productMinerals = productNutrientsInformation.getMinerals();
        Collection<Proximate> productProximates = productNutrientsInformation.getProximates();
        Collection<Lipid> productLipids = productNutrientsInformation.getLipids();
        Collection<Vitamin> productVitamins = productNutrientsInformation.getVitamins();
        Collection<OtherNutrient> productOtherNutrients = productNutrientsInformation.getOtherNutrients();
        
        productMinerals.forEach(productMineral -> {
            if (mineralMap.containsKey(productMineral.getMineralType())) {
                productMineral.setQuantity(mineralMap.get(productMineral.getMineralType()));
            }
        });
        productProximates.forEach(productProximate -> {
            if (proximateMap.containsKey(productProximate.getProximateType())) {
                productProximate.setQuantity(proximateMap.get(productProximate.getProximateType()));
            }
        });
        productLipids.forEach(productLipid -> {
            if (lipidMap.containsKey(productLipid.getLipidType())) {
                productLipid.setQuantity(lipidMap.get(productLipid.getLipidType()));
            }
        });
        productVitamins.forEach(productVitamin -> {
            if (vitaminMap.containsKey(productVitamin.getVitaminType())) {
                productVitamin.setQuantity(vitaminMap.get(productVitamin.getVitaminType()));
            }
        });
        productOtherNutrients.forEach(productOtherNutrient -> {
            if (otherNutrientMap.containsKey(productOtherNutrient.getOtherNutrientType())) {
                productOtherNutrient.setQuantity(otherNutrientMap.get(productOtherNutrient.getOtherNutrientType()));
            }
        });

        return productNutrientsInformation;
    }
}
