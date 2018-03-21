package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class VitaminType extends BaseEntity {

    @OneToMany(mappedBy = "vitaminType")
    private Collection<Vitamin> vitamins;


    public Collection<Vitamin> getVitamins() {
        return vitamins;
    }

    public void setVitamins(Collection<Vitamin> vitamins) {
        this.vitamins = vitamins;
    }
}
