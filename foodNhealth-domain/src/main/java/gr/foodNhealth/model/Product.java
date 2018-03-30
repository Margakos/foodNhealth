package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Product extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String photoPath;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Collection<ProductPackage> productPackages;

    @NotNull
    @ManyToOne(optional = false)
    private Ingredient ingredient;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Collection<ProductPackage> getProductPackages() {
        return productPackages;
    }

    public void setProductPackages(Collection<ProductPackage> productPackages) {
        this.productPackages = productPackages;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }
}
