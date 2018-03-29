package gr.foodNhealth.service;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private UtilsService utils;

    @Autowired
    private NutrientsInformationRepository nutrientsInformationRepository;

    @Autowired
    private ProximateService proximateService;

    @Autowired
    private MineralService mineralService;

    @Autowired
    private VitaminService vitaminService;

    @Autowired
    private LipidService lipidService;

    @Autowired
    private OtherNutrientService otherNutrientService;

    @Transactional
    public Product initNewProduct (Product product, Ingredient ingredient) {
        NutrientsInformation productNutrientsInformation = new NutrientsInformation();
        productNutrientsInformation.setDeleted(false);
        productNutrientsInformation.setIsActive(true);
        productNutrientsInformation = nutrientsInformationRepository.save(productNutrientsInformation);

        productNutrientsInformation.setLipids(lipidService.initProductLipids(productNutrientsInformation));
        productNutrientsInformation.setProximates(proximateService.initProductProximates(productNutrientsInformation));
        productNutrientsInformation.setMinerals(mineralService.initProductMinerals(productNutrientsInformation));
        productNutrientsInformation.setVitamins(vitaminService.initProductVitamins(productNutrientsInformation));
        productNutrientsInformation.setOtherNutrients(otherNutrientService.initProductOtherNutrients(productNutrientsInformation));
        productNutrientsInformation = utils.propagateNutrientsInformationToProduct(productNutrientsInformation, ingredient.getNutrientsInformation());

        product.setNutrientsInformation(productNutrientsInformation);
        return product;
    }
}
