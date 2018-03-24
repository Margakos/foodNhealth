package gr.foodNhealth.model;

import javax.persistence.*;

@Entity

public class Ingredient extends BaseEntity {

    @Column
    private String name;

    @Column
    private String photoPath;

    @OneToOne(cascade = CascadeType.REMOVE)
    private FoodCategory foodCategory;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column
    private AvailableForm availableForm;

    @OneToOne(cascade = CascadeType.REMOVE)
    private NutrientsInformation nutrientsInformation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public enum AvailableForm {
        GRAMS, PIECES, SLICES
    }
}
