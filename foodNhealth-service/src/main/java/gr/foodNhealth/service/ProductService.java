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
        NutrientsInformation nutrientsInformation = new NutrientsInformation();
        nutrientsInformation.setTitle("Product");
        nutrientsInformation.setDeleted(false);
        nutrientsInformation.setIsActive(true);
        nutrientsInformation = nutrientsInformationRepository.save(nutrientsInformation);

        nutrientsInformation.setLipids(lipidService.initProductLipids(nutrientsInformation));
        nutrientsInformation.setProximates(proximateService.initProductProximates(nutrientsInformation));
        nutrientsInformation.setMinerals(mineralService.initProductMinerals(nutrientsInformation));
        nutrientsInformation.setVitamins(vitaminService.initProductVitamins(nutrientsInformation));
        nutrientsInformation.setOtherNutrients(otherNutrientService.initProductOtherNutrients(nutrientsInformation));
        nutrientsInformation = utils.propagateNutrientsInformationToProduct(nutrientsInformation, ingredient.getNutrientsInformation());

        product.setNutrientsInformation(nutrientsInformation);
        return product;
    }
}
