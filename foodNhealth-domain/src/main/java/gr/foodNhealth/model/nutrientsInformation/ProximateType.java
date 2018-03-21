package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class ProximateType extends BaseEntity {

    @OneToMany(mappedBy = "proximateType")
    private Collection<Proximate> proximates;


    public Collection<Proximate> getProximates() {
        return proximates;
    }

    public void setProximates(Collection<Proximate> proximates) {
        this.proximates = proximates;
    }
}
