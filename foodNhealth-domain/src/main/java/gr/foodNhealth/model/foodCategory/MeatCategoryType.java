package gr.foodNhealth.model.foodCategory;

import gr.foodNhealth.model.BaseEntity;
import gr.foodNhealth.model.FoodCategory;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class MeatCategoryType extends BaseEntity {

    @OneToMany(mappedBy = "meatCategoryType")
    private Collection<FoodCategory> foodCategories;


    public Collection<FoodCategory> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(Collection<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
    }
}
