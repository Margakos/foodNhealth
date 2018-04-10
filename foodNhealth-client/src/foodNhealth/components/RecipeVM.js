export default {
  name: 'recipe',
  components: {},
  data: function () {
    return {
      visible: false,
      recipe: initRecipe(),
      ingredients: [],
      cuisines: [],
      recipeCategories: [],
      ingredientPortions: [],
      quantityShown: [],
      piecesShown: [],
      rules: {
        name: {
          required: true,
          max: 255
        },
        ingredient: {
          required: false
        },
        pieces: {
          required: true,
          min_value: 0
        },
        cuisine: {
          required: true
        },
        instruction: {
          required: false
        },
        recipeCategory: {
          required: true
        },
        quantity: {
          required: true,
          min_value: 0
        }
      },
      ingredientPortionsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'ingredient',
          label: 'Συστατικό'
        },
        {
          key: 'quantity_slices',
          label: ' Ποσότητα'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ]
    }
  },
  created () {
    Promise.all([this.getIngredients(), this.getCuisines(), this.getRecipeCategories()]).then(([ingredients, cuisines, recipeCategories]) => {
      this.ingredients = ingredients.data._embedded.ingredients
      this.cuisines = cuisines.data._embedded.cuisines
      this.recipeCategories = recipeCategories.data._embedded.recipeCategories
    })
    console.log('Recipe created')
  },
  mounted () {
    this.$events.$on('edit-recipe', eventData => this.onEditRecipe(eventData))
    console.log('Recipe mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-recipe')
    console.log('Recipe destroyed')
  },
  computed: {
    isDeletable () {
      return this.recipe.id != null
    }
  },
  methods: {
    refreshIngredientPortions () {
      let _self = this
      this.ingredientPortions = []
      this.quantityShown = []
      this.piecesShown = []
      this.$http.get(this.recipe._links.ingredientPortions.href + '?projection=inlinedIngredientPortion').then(response => {
        this.ingredientPortions = response.data._embedded.ingredientPortions
        this.ingredientPortions.forEach(function (ingredientPortion, index) {
          let quantified = ingredientPortion.ingredient.quantified
          _self.quantityShown.push(!quantified)
          _self.piecesShown.push(quantified)
        })
      })
    },
    onEditRecipe (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('recipes/' + eventData + '?projection=inlinedRecipe').then(response => {
          this.recipe = response.data
          this.refreshIngredientPortions()
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        // Add new Recipe
        Object.assign(this.$data.recipe, initRecipe())
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll('generalForm').then((result) => {
        if (!result) {
          return
        }
        if (this.ingredientPortions.length === 0) {
          this.$validator.errors.add({field: 'ingredientPortions', msg: this.$messages.fieldInsertsEmpty, scope: 'generalForm'})
        }
        let _self = this
        let tempRecipe = Object.assign({}, this.recipe)
        if (this.recipe.id != null) {
          // existing recipe , update
          this.$http.patch('recipes/' + this.recipe.id + '?projection=inlinedRecipe', tempRecipe, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          this.$http.post('recipes?projection=inlinedRecipe', tempRecipe, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => {
            let tempIngredientPortions = Object.assign([{}], this.ingredientPortions)
            this.$http.post('recipes/' + response.data.id + '/ingredientPortions', tempIngredientPortions).then(resp => {
              this.handleSuccess(response)
            }).catch(e => this.handleError(e))
          }).catch(e => this.handleError(e))
        }
      })
    },
    cancel () {
      this.visible = false
    },
    handleError (e) {
      console.log(e)
      this.error(this.$messages.errorAction)
    },
    confirmDelete () {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('recipes/' + this.recipe.id).then(response => {
          this.visible = false
          this.success(this.$messages.successAction)
          console.log('fire recipe-edited event')
          this.$events.fire('recipe-edited', this.recipe)
        })
          .catch(e => {
            // only 409 errors should end up here
            this.error(this.$messages.errorDeleteDependencies)
          })
      })
    },
    invalidateAll () {
      this.$validator.reset().then(() => {
        this.errors.clear('generalForm')
      })
      this.ingredientPortions = []
    },
    handleSuccess (response) {
      this.recipe = response.data
      this.success(this.$messages.successAction)
      this.refreshIngredientPortions()
      console.log('fire recipe-edited event')
      this.$events.fire('recipe-edited', this.recipe)
    },
    transformRequest (data, headers) {
      data.cuisine = this.convertEntityToURI(data.cuisine)
      data.recipeCategory = this.convertEntityToURI(data.recipeCategory)
      data.ingredientPortions = data.ingredientPortions != null ? this.convertEntitiesToURIs(data.ingredientPortions) : null
      return JSON.stringify(data)
    },
    addIngredientPortion () {
      this.ingredientPortions.push(initIngredientPortion())
      this.quantityShown.push(false)
      this.piecesShown.push(false)
    },
    deleteIngredientPortion (ingredientPortion) {
      this.ingredientPortions.splice(this.ingredientPortions.indexOf(ingredientPortion), 1)
      this.quantityShown.splice(this.ingredientPortions.indexOf(ingredientPortion), 1)
      this.piecesShown.splice(this.ingredientPortions.indexOf(ingredientPortion), 1)
    },
    ingredientChanged (index) {
      let ingredient = this.ingredientPortions[index].ingredient
      if (ingredient.quantified) {
        this.quantityShown[index] = false
        this.piecesShown[index] = true
      } else {
        this.quantityShown[index] = true
        this.piecesShown[index] = false
      }
    }
  }
}

function initRecipe () {
  return {
    id: null,
    name: '',
    photoPath: null,
    instruction: '',
    recipeCategory: null,
    cuisine: null
  }
}

function initIngredientPortion () {
  return {
    id: null,
    recipe: null,
    ingredient: null,
    quantity: 0,
    pieces: 0
  }
}
