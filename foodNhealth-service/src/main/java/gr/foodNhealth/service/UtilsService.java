package gr.foodNhealth.service;

import gr.foodNhealth.model.IngredientPortion;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.SelectedProductPackage;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class UtilsService {
    @Autowired
    private NutrientsInformationRepository nutrientsInformationRepository;

    public NutrientsInformation propagateNutrientsInformationToProduct (NutrientsInformation productNutrientsInformation,
                                                                        NutrientsInformation nutrientsInformation) {
        BigDecimal sourceQuantity = new BigDecimal(100);
        BigDecimal targetQuantity = BigDecimal.ZERO;
        return productNutrientsInformation.add(nutrientsInformation, sourceQuantity, targetQuantity);
    }

    public NutrientsInformation propagateNutrientsInformationToSelectedRecipe (NutrientsInformation selectedRecipeNutrientsInformation,
                                                                       Collection<SelectedProductPackage> selectedProductPackages) {
        NutrientsInformation nutrientsInformation;
        BigDecimal temp = BigDecimal.ZERO;
        for (SelectedProductPackage selectedProductPackage : selectedProductPackages) {
            BigDecimal sourceQuantity = selectedProductPackage.getQuantity();
            BigDecimal targetQuantity = temp;
            temp = sourceQuantity.add(targetQuantity);
            nutrientsInformation = nutrientsInformationRepository.getOne(selectedProductPackage.getProductPackage().getProduct().getNutrientsInformation().getId());
            selectedRecipeNutrientsInformation = selectedRecipeNutrientsInformation.add(nutrientsInformation, sourceQuantity, targetQuantity);
        }
        return selectedRecipeNutrientsInformation;
    }

    public BigDecimal getQuantity (SelectedProductPackage selectedProductPackage, IngredientPortion ingredientPortion) {
        if (ingredientPortion.getQuantity().doubleValue() != BigDecimal.ZERO.doubleValue()) {
            return ingredientPortion.getQuantity();
        }

        Integer productPieces = ingredientPortion.getPieces();
        BigDecimal productPackageGrams = selectedProductPackage.getProductPackage().getQuantity();
        BigDecimal quantity = productPackageGrams.divide(new BigDecimal(productPieces), 12, BigDecimal.ROUND_HALF_UP);
        return quantity;
    }
}
