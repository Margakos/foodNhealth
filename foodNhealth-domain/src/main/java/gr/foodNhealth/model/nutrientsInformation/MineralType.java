package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class MineralType extends BaseEntity {

    @OneToMany(mappedBy = "mineralType")
    private Collection<Mineral> minerals;


    public Collection<Mineral> getMinerals() {
        return minerals;
    }

    public void setMinerals(Collection<Mineral> minerals) {
        this.minerals = minerals;
    }
}
