package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class SelectedRecipe extends BaseEntity {

    @Basic
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @ManyToOne(optional = false)
    private Recipe recipe;

    @OneToMany(mappedBy = "selectedRecipe", cascade = CascadeType.REMOVE)
    private Collection<SelectedProductPackage> selectedProductPackages;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Collection<SelectedProductPackage> getSelectedProductPackages() {
        return selectedProductPackages;
    }

    public void setSelectedProductPackages(Collection<SelectedProductPackage> selectedProductPackages) {
        this.selectedProductPackages = selectedProductPackages;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }
}
