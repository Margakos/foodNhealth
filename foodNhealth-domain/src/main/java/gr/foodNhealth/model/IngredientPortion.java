package gr.foodNhealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class IngredientPortion extends BaseEntity {

    @Min(0)
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Min(0)
    @Column
    private Integer pieces;

    @NotNull
    @ManyToOne(optional = false)
    private Ingredient ingredient;

    @NotNull
    @ManyToOne(optional = false)
    private Recipe recipe;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
