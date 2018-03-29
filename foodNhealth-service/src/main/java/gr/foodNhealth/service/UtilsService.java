package gr.foodNhealth.service;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.model.nutrientsInformation.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UtilsService {

    public NutrientsInformation propagateNutrientsInformationToProduct (NutrientsInformation productNutrientsInformation,
                                                                        NutrientsInformation nutrientsInformation) {
        return productNutrientsInformation.add(nutrientsInformation);
    }

    public NutrientsInformation propagateNutrientsInformationToRecipe (NutrientsInformation recipeNutrientsInformation,
                                                                       Collection<Product> products) {
        for (Product product : products) {
            recipeNutrientsInformation = recipeNutrientsInformation.add(product.getNutrientsInformation());
        }
        return recipeNutrientsInformation;
    }
}
