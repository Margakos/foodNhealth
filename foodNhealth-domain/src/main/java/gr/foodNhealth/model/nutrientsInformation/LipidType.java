package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class LipidType extends BaseEntity {

    @OneToMany
    private Collection<Lipid> lipids;


    public Collection<Lipid> getLipids() {
        return lipids;
    }

    public void setLipids(Collection<Lipid> lipids) {
        this.lipids = lipids;
    }
}
