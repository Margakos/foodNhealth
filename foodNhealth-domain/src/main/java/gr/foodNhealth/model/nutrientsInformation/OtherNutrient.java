package gr.foodNhealth.model.nutrientsInformation;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.NutrientsInformation;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.other.Alcohol;
import gr.foodNhealth.model.nutrientsInformation.basicSunstances.other.Caffeine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class OtherNutrient extends BaseEntity {

    @OneToOne
    private NutrientsInformation nutrientsInformation;

    @OneToOne(mappedBy = "otherNutrient", cascade = CascadeType.REMOVE)
    private Alcohol alcohol;

    @OneToOne(mappedBy = "otherNutrient", cascade = CascadeType.REMOVE)
    private Caffeine caffeine;


    public NutrientsInformation getNutrientsInformation() {
        return nutrientsInformation;
    }

    public void setNutrientsInformation(NutrientsInformation nutrientsInformation) {
        this.nutrientsInformation = nutrientsInformation;
    }

    public Alcohol getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Alcohol alcohol) {
        this.alcohol = alcohol;
    }

    public Caffeine getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(Caffeine caffeine) {
        this.caffeine = caffeine;
    }
}
