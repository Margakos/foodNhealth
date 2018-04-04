package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class ProductPackage extends BaseEntity {

    @ManyToOne
    private Supermarket supermarket;

    @NotNull
    @ManyToOne(optional = false)
    private Product product;

    @Min(0)
    @NotNull
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Min(0)
    @NotNull
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal price;

    @Basic
    @Min(0)
    @Column
    private Integer numPieces;

    @OneToMany(mappedBy = "productPackage", cascade = CascadeType.REMOVE)
    private Collection<SelectedProductPackage> selectedProductPackages;


    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(Integer numPieces) {
        this.numPieces = numPieces;
    }

    public Collection<SelectedProductPackage> getSelectedProductPackages() {
        return selectedProductPackages;
    }

    public void setSelectedProductPackages(Collection<SelectedProductPackage> selectedProductPackages) {
        this.selectedProductPackages = selectedProductPackages;
    }
}
