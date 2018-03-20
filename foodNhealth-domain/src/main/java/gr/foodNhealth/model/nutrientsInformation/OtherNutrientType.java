package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class OtherNutrientType extends BaseEntity {

    @OneToMany
    private Collection<OtherNutrient> otherNutrients;


    public Collection<OtherNutrient> getOtherNutrients() {
        return otherNutrients;
    }

    public void setOtherNutrients(Collection<OtherNutrient> otherNutrients) {
        this.otherNutrients = otherNutrients;
    }
}
