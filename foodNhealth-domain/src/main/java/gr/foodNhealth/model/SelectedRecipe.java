package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class SelectedRecipe extends BaseEntity {

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "selectedRecipe", cascade = CascadeType.REMOVE)
    private Collection<SelectedProductPackage> selectedProductPackages;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
