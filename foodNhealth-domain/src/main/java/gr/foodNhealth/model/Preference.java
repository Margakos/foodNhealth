package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Preference extends BaseEntity {

    @OneToOne(mappedBy = "preference")
    private Client client;

    @ManyToMany
    private Collection<Ingredient> dislikedIngredients;

    @ManyToMany
    private Collection<Allergy> allergies;

    @Basic
    @Min(0)
    @Column(precision = 5, scale = 2)
    private BigDecimal weightPerWeek;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Ingredient> getDislikedIngredients() {
        return dislikedIngredients;
    }

    public void setDislikedIngredients(Collection<Ingredient> dislikedIngredients) {
        this.dislikedIngredients = dislikedIngredients;
    }

    public Collection<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Collection<Allergy> allergies) {
        this.allergies = allergies;
    }

    public BigDecimal getWeightPerWeek() {
        return weightPerWeek;
    }

    public void setWeightPerWeek(BigDecimal weightPerWeek) {
        this.weightPerWeek = weightPerWeek;
    }
}
