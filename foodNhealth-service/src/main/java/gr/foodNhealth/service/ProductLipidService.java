package gr.foodNhealth.service;

import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.ProductLipid;
import gr.foodNhealth.repository.ProductLipidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductLipidService {

    @Autowired
    private ProductLipidRepository productLipidRepository;

    public Collection<ProductLipid> initProductLipids (ProductNutrientsInformation productNutrientsInformation) {
        List<ProductLipid> productLipids = new ArrayList<>();
        productLipids.add(new ProductLipid("Saturated", productNutrientsInformation));
        productLipids.add(new ProductLipid("Monounsaturated", productNutrientsInformation));
        productLipids.add(new ProductLipid("Polyunsaturated", productNutrientsInformation));
        productLipids.add(new ProductLipid("Trans", productNutrientsInformation));
        productLipids.forEach(lipid -> {
            lipid.setIsActive(true);
            lipid.setDeleted(false);
        });
        productLipidRepository.save(productLipids);
        return productLipids;
    }
}
