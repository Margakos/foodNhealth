package gr.foodNhealth.service;

import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.ProductProximate;
import gr.foodNhealth.repository.ProductProximateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductProximateService {

    @Autowired
    private ProductProximateRepository productProximateRepository;

    public Collection<ProductProximate> initProductProximates (ProductNutrientsInformation productNutrientsInformation) {
        List<ProductProximate> productProximates = new ArrayList<>();
        productProximates.add(new ProductProximate("Water", productNutrientsInformation));
        productProximates.add(new ProductProximate("Energy", productNutrientsInformation));
        productProximates.add(new ProductProximate("Protein", productNutrientsInformation));
        productProximates.add(new ProductProximate("Fat", productNutrientsInformation));
        productProximates.add(new ProductProximate("Carbohydrate", productNutrientsInformation));
        productProximates.add(new ProductProximate("Sugars", productNutrientsInformation));
        productProximates.add(new ProductProximate("Fibers", productNutrientsInformation));
        productProximates.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        productProximateRepository.save(productProximates);
        return productProximates;
    }
}
