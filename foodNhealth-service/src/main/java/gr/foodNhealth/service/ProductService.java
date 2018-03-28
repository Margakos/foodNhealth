package gr.foodNhealth.service;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.ProductNutrientsInformation;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import gr.foodNhealth.repository.ProductNutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private UtilsService utils;

    @Autowired
    private ProductNutrientsInformationRepository productNutrientsInformationRepository;

    @Autowired
    private ProductProximateService productProximateService;

    @Autowired
    private ProductMineralService productMineralService;

    @Autowired
    private ProductVitaminService productVitaminService;

    @Autowired
    private ProductLipidService productLipidService;

    @Autowired
    private ProductOtherNutrientService productOtherNutrientService;

    @Transactional
    public Product initNewProduct (Product product, Ingredient ingredient) {
        ProductNutrientsInformation productNutrientsInformation = new ProductNutrientsInformation();
        productNutrientsInformation.setDeleted(false);
        productNutrientsInformation.setIsActive(true);
        productNutrientsInformation = productNutrientsInformationRepository.save(productNutrientsInformation);

        productNutrientsInformation.setProductLipids(productLipidService.initProductLipids(productNutrientsInformation));
        productNutrientsInformation.setProductProximates(productProximateService.initProductProximates(productNutrientsInformation));
        productNutrientsInformation.setProductMinerals(productMineralService.initProductMinerals(productNutrientsInformation));
        productNutrientsInformation.setProductVitamins(productVitaminService.initProductVitamins(productNutrientsInformation));
        productNutrientsInformation.setProductOtherNutrients(productOtherNutrientService.initProductOtherNutrients(productNutrientsInformation));
        productNutrientsInformation = utils.propagateNutrientsInformation(productNutrientsInformation, ingredient.getNutrientsInformation());

        product.setProductNutrientsInformation(productNutrientsInformation);
        return product;
    }
}
