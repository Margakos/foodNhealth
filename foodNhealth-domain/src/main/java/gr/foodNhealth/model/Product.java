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

    @Basic
    @Enumerated(EnumType.STRING)
    @Column
    private AvailableForm availableForm;

    @OneToMany(mappedBy = "product")
    private Collection<ProductPackage> productPackages;

    @NotNull
    @ManyToOne(optional = false)
    private Ingredient ingredient;

    @OneToOne
    private ProductNutrientsInformation productNutrientsInformation;


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

    public AvailableForm getAvailableForm() {
        return availableForm;
    }

    public void setAvailableForm(AvailableForm availableForm) {
        this.availableForm = availableForm;
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

    public ProductNutrientsInformation getProductNutrientsInformation() {
        return productNutrientsInformation;
    }

    public void setProductNutrientsInformation(ProductNutrientsInformation productNutrientsInformation) {
        this.productNutrientsInformation = productNutrientsInformation;
    }

    public enum AvailableForm {
        GRAMS, PIECES, SLICES
    }
}
