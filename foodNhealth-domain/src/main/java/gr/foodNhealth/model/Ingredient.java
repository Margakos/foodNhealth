package gr.foodNhealth.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity

public class Ingredient extends BaseEntity {

    @Column
    private String name;

    @NotNull
    @Min(0)
    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Column
    private String photoPath;

    @NotNull
    @OneToOne(mappedBy = "ingredient")
    private FoodCategory foodCategory;

    @Basic
    @NotNull
    @Enumerated(EnumType.STRING)
    private AvailableForm availableForm;

    @NotNull
    @OneToOne(mappedBy = "ingredient")
    private NutrientsInformation nutrientsInformation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public AvailableForm getAvailableForm() {
        return availableForm;
    }

    public void setAvailableForm(AvailableForm availableForm) {
        this.availableForm = availableForm;
    }

    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }
}
