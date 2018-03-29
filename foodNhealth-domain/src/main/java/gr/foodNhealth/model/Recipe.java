package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
public class Recipe extends BaseEntity {

    @ManyToOne
    private RecipeCategory recipeCategory;

    @ManyToOne
    private Cuisine cuisine;

    @ManyToMany
    private Collection<Product> products;


}
