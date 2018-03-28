package gr.foodNhealth.service;

import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.ProductMineral;
import gr.foodNhealth.repository.ProductMineralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductMineralService {

    @Autowired
    private ProductMineralRepository productMineralRepository;

    public Collection<ProductMineral> initProductMinerals (ProductNutrientsInformation productNutrientsInformation) {
        List<ProductMineral> productMinerals = new ArrayList<>();
        productMinerals.add(new ProductMineral("Calcium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Chromium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Copper", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Fluoride", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Iodine", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Iron", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Magnesium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Manganese", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Molybdenum", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Phosphorus", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Selenium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Zinc", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Potassium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Sodium", productNutrientsInformation));
        productMinerals.add(new ProductMineral("Chloride", productNutrientsInformation));
        productMinerals.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        productMineralRepository.save(productMinerals);
        return productMinerals;
    }
}
