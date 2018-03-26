package gr.foodNhealth.model;

import gr.foodNhealth.model.nutrientsInformation.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
public class ProductNutrientsInformation extends BaseEntity {

    @OneToOne(mappedBy = "productNutrientsInformation")
    private Product product;

    @OneToMany(mappedBy = "productNutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<ProductMineral> productMinerals;

    @OneToMany(mappedBy = "productNutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<ProductProximate> productProximates;

    @OneToMany(mappedBy = "productNutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<ProductVitamin> productVitamins;

    @OneToMany(mappedBy = "productNutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<ProductLipid> productLipids;

    @OneToMany(mappedBy = "productNutrientsInformation", cascade = CascadeType.REMOVE)
    private Collection<ProductOtherNutrient> productOtherNutrients;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<ProductMineral> getProductMinerals() {
        return productMinerals;
    }

    public void setProductMinerals(Collection<ProductMineral> productMinerals) {
        this.productMinerals = productMinerals;
    }

    public Collection<ProductProximate> getProductProximates() {
        return productProximates;
    }

    public void setProductProximates(Collection<ProductProximate> productProximates) {
        this.productProximates = productProximates;
    }

    public Collection<ProductVitamin> getProductVitamins() {
        return productVitamins;
    }

    public void setProductVitamins(Collection<ProductVitamin> productVitamins) {
        this.productVitamins = productVitamins;
    }

    public Collection<ProductLipid> getProductLipids() {
        return productLipids;
    }

    public void setProductLipids(Collection<ProductLipid> productLipids) {
        this.productLipids = productLipids;
    }

    public Collection<ProductOtherNutrient> getProductOtherNutrients() {
        return productOtherNutrients;
    }

    public void setProductOtherNutrients(Collection<ProductOtherNutrient> productOtherNutrients) {
        this.productOtherNutrients = productOtherNutrients;
    }
}
