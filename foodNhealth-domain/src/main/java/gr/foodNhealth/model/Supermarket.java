package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Supermarket extends BaseEntity {

    @OneToMany(mappedBy = "supermarket")
    private Collection<ProductPackage> productPackages;

    public Collection<ProductPackage> getProductPackages() {
        return productPackages;
    }

    public void setProductPackages(Collection<ProductPackage> productPackages) {
        this.productPackages = productPackages;
    }
}
