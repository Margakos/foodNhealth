export default {
  name: 'recipe',
  components: {},
  data: function () {
    return {
      visible: false,
      recipe: initRecipe(),
      minerals: [],
      mineralTypes: [],
      vitamins: [],
      vitaminTypes: [],
      lipids: [],
      lipidTypes: [],
      proximates: [],
      proximateTypes: [],
      otherNutrients: [],
      otherNutrientTypes: [],
      products: [],
      cuisines: [],
      recipeCategories: [],
      rules: {
        name: {
          required: true,
          max: 255
        },
        products: {
          required: false
        },
        availableForm: {
          required: true
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
      mineralsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'mineralType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'unit',
          label: 'Μονάδα Μέτρησης',
          thClass: 'text-right',
          tdClass: 'text-center'
        }
      ],
      vitaminsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'vitaminType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'unit',
          label: 'Μονάδα Μέτρησης',
          thClass: 'text-right',
          tdClass: 'text-center'
        }
      ],
      lipidsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'lipidType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'unit',
          label: 'Μονάδα Μέτρησης',
          thClass: 'text-right',
          tdClass: 'text-center'
        }
      ],
      proximatesFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'proximateType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'unit',
          label: 'Μονάδα Μέτρησης',
          thClass: 'text-right',
          tdClass: 'text-center'
        }
      ],
      otherNutrientsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'otherNutrientType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'unit',
          label: 'Μονάδα Μέτρησης',
          thClass: 'text-right',
          tdClass: 'text-center'
        }
      ]
    }
  },
  created () {
    Promise.all([this.getProducts(), this.getCuisines(), this.getRecipeCategories()]).then(([products, cuisines, recipeCategories]) => {
      this.products = products.data._embedded.products
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
    refreshNutrientsInformation () {
      this.refreshMinerals()
      this.refreshVitamins()
      this.refreshLipids()
      this.refreshProximates()
      this.refreshOtherNutrients()
    },
    refreshMinerals () {
      let _self = this
      this.$http.get(this.recipe.nutrientsInformation._links.minerals.href + '?projection=inlinedMineral').then(response => {
        this.minerals = response.data._embedded.minerals
        this.minerals.forEach(function (mineral, index) {
          _self.mineralTypes.push(mineral.mineralType)
        })
      })
    },
    refreshVitamins () {
      let _self = this
      this.$http.get(this.recipe.nutrientsInformation._links.vitamins.href + '?projection=inlinedVitamin').then(response => {
        this.vitamins = response.data._embedded.vitamins
        this.vitamins.forEach(function (vitamin, index) {
          _self.vitaminTypes.push(vitamin.vitaminType)
        })
      })
    },
    refreshLipids () {
      let _self = this
      this.$http.get(this.recipe.nutrientsInformation._links.lipids.href + '?projection=inlinedLipid').then(response => {
        this.lipids = response.data._embedded.lipids
        this.lipids.forEach(function (lipid, index) {
          _self.lipidTypes.push(lipid.lipidType)
        })
      })
    },
    refreshProximates () {
      let _self = this
      this.$http.get(this.recipe.nutrientsInformation._links.proximates.href + '?projection=inlinedProximate').then(response => {
        this.proximates = response.data._embedded.proximates
        this.proximates.forEach(function (proximate, index) {
          _self.proximateTypes.push(proximate.proximateType)
        })
      })
    },
    refreshOtherNutrients () {
      let _self = this
      this.$http.get(this.recipe.nutrientsInformation._links.otherNutrients.href + '?projection=inlinedOtherNutrient').then(response => {
        this.otherNutrients = response.data._embedded.otherNutrients
        this.otherNutrients.forEach(function (otherNutrient, index) {
          _self.otherNutrientTypes.push(otherNutrient.otherNutrientType)
        })
      })
    },
    refreshProducts () {
      this.$http.get(this.recipe._links.products.href + '?projection=inlinedProduct').then(response => {
        this.recipe.products = response.data._embedded.products
      })
    },
    onEditRecipe (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('recipes/' + eventData + '?projection=inlinedRecipe').then(response => {
          this.recipe = response.data
          this.refreshNutrientsInformation()
          this.refreshProducts()
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
        if (this.recipe.products.length === 0) {
          this.$validator.errors.add({field: 'products', msg: this.$messages.productsEmpty, scope: 'generalForm'})
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
            this.handleSuccess(response)
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
    },
    handleSuccess (response) {
      this.recipe = response.data
      this.success(this.$messages.successAction)
      this.refreshProducts()
      this.refreshNutrientsInformation()
      console.log('fire recipe-edited event')
      this.$events.fire('recipe-edited', this.recipe)
    },
    transformRequest (data, headers) {
      data.cuisine = this.convertEntityToURI(data.cuisine)
      data.recipeCategory = this.convertEntityToURI(data.recipeCategory)
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      data.products = this.convertEntitiesToURIs(data.products)
      return JSON.stringify(data)
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
    cuisine: null,
    nutrientsInformation: null,
    availableForm: null,
    products: []
  }
}
