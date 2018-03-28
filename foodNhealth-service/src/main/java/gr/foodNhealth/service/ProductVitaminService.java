package gr.foodNhealth.service;

import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.ProductVitamin;
import gr.foodNhealth.repository.ProductVitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductVitaminService {

    @Autowired
    private ProductVitaminRepository productVitaminRepository;

    public Collection<ProductVitamin> initProductVitamins (ProductNutrientsInformation productNutrientsInformation) {
        List<ProductVitamin> productVitamins = new ArrayList<>();
        productVitamins.add(new ProductVitamin("Vitamin A", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B1", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B2", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B3", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B5", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B6", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B7", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B9", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin B12", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin C", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin D", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin E", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Vitamin K", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Choline, nutrientsInformation", productNutrientsInformation));
        productVitamins.add(new ProductVitamin("Carotenoids", productNutrientsInformation));
        productVitamins.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        productVitaminRepository.save(productVitamins);
        return productVitamins;
    }
}
