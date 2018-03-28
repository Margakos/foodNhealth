package gr.foodNhealth.listener;

import gr.foodNhealth.model.Ingredient;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.Product;
import gr.foodNhealth.repository.IngredientRepository;
import gr.foodNhealth.repository.NutrientsInformationRepository;
import gr.foodNhealth.service.IngredientService;
import gr.foodNhealth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Ingredient.class)
public class ProductEventHandler {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ProductService productService;

    @HandleBeforeCreate
    public void handleBeforeCreate (Product product) {
        Ingredient ingredient = ingredientRepository.findOne(product.getIngredient().getId());
        product = productService.initNewProduct(product, ingredient);
    }
}
