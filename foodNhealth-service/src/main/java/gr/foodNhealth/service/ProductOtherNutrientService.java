package gr.foodNhealth.service;

import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.ProductOtherNutrient;
import gr.foodNhealth.repository.ProductOtherNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductOtherNutrientService {

    @Autowired
    private ProductOtherNutrientRepository productOtherNutrientRepository;

    public Collection<ProductOtherNutrient> initProductOtherNutrients (ProductNutrientsInformation productNutrientsInformation) {
        List<ProductOtherNutrient> productOtherNutrients = new ArrayList<>();
        productOtherNutrients.add(new ProductOtherNutrient("Alcohol", productNutrientsInformation));
        productOtherNutrients.add(new ProductOtherNutrient("Caffeine", productNutrientsInformation));
        productOtherNutrients.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        productOtherNutrientRepository.save(productOtherNutrients);
        return productOtherNutrients;
    }
}
