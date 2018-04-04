export default {
  name: 'ingredient',
  data: function () {
    return {
      visible: false,
      ingredient: initIngredient(),
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
      foodCategoryCoreTypes: [],
      foodCategorySubTypes: [],
      meatCategoryTypes: [],
      meatCategoryType: null,
      rules: {
        name: {
          required: true,
          max: 255
        },
        foodCategoryCoreType: {
          required: true
        },
        foodCategorySubType: {
          required: true
        },
        meatCategoryType: {
          required: true
        },
        quantity: {
          required: true,
          min_value: 0
        },
        quantified: {
          required: false
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
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
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
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
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
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
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
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
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
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ]
    }
  },
  created () {
    Promise.all([this.getFoodCategoryCoreTypes()])
      .then(([foodCategoryCoreTypes]) => {
        this.foodCategoryCoreTypes = foodCategoryCoreTypes.data._embedded.foodCategoryCoreTypes
      })
    console.log('Ingredient created')
  },
  mounted () {
    this.$events.$on('edit-ingredient', eventData => this.onEditIngredient(eventData))
    console.log('Ingredient mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-ingredient')
    console.log('Ingredient destroyed')
  },
  computed: {
    isDeletable () {
      return this.ingredient.id != null
    },
    isMeatCategoryTypeDisabled () {
      return this.ingredient.foodCategorySubType != null && this.ingredient.foodCategorySubType.title.split(' ')[1] !== 'Fat'
    },
    isFoodCategorySubTypeDisabled () {
      return this.ingredient.foodCategoryCoreType == null
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
      this.$http.get(this.ingredient.nutrientsInformation._links.minerals.href + '?projection=inlinedMineral').then(response => {
        this.minerals = response.data._embedded.minerals
        this.minerals.forEach(function (mineral, index) {
          _self.mineralTypes.push(mineral.mineralType)
        })
      })
    },
    refreshVitamins () {
      let _self = this
      this.$http.get(this.ingredient.nutrientsInformation._links.vitamins.href + '?projection=inlinedVitamin').then(response => {
        this.vitamins = response.data._embedded.vitamins
        this.vitamins.forEach(function (vitamin, index) {
          _self.vitaminTypes.push(vitamin.vitaminType)
        })
      })
    },
    refreshLipids () {
      let _self = this
      this.$http.get(this.ingredient.nutrientsInformation._links.lipids.href + '?projection=inlinedLipid').then(response => {
        this.lipids = response.data._embedded.lipids
        this.lipids.forEach(function (lipid, index) {
          _self.lipidTypes.push(lipid.lipidType)
        })
      })
    },
    refreshProximates () {
      let _self = this
      this.$http.get(this.ingredient.nutrientsInformation._links.proximates.href + '?projection=inlinedProximate').then(response => {
        this.proximates = response.data._embedded.proximates
        this.proximates.forEach(function (proximate, index) {
          _self.proximateTypes.push(proximate.proximateType)
        })
      })
    },
    refreshOtherNutrients () {
      let _self = this
      this.$http.get(this.ingredient.nutrientsInformation._links.otherNutrients.href + '?projection=inlinedOtherNutrient').then(response => {
        this.otherNutrients = response.data._embedded.otherNutrients
        this.otherNutrients.forEach(function (otherNutrient, index) {
          _self.otherNutrientTypes.push(otherNutrient.otherNutrientType)
        })
      })
    },
    refreshFoodCategorySubTypes (foodCategoryCoreType) {
      this.getFoodCategorySubTypes(foodCategoryCoreType.id).then(response => {
        this.foodCategorySubTypes = response.data._embedded.foodCategorySubTypes
      })
    },
    refreshMeatCategoryTypes (foodCategorySubType) {
      this.getMeatCategoryTypes(foodCategorySubType.id).then(response => {
        this.meatCategoryTypes = response.data._embedded.meatCategoryTypes
      })
    },
    onEditIngredient (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('ingredients/' + eventData + '?projection=inlinedIngredient').then(response => {
          this.ingredient = response.data
          this.refreshNutrientsInformation()
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        // Add new Ingredient
        Object.assign(this.$data.ingredient, initIngredient())
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll('generalForm').then((result) => {
        if (!result) {
          return
        }
        let _self = this
        let tempIngredient = Object.assign({}, this.ingredient)
        if (this.ingredient.id != null) {
          // existing ingredient , update
          this.$http.patch('ingredients/' + this.ingredient.id + '?projection=inlinedIngredient', tempIngredient, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          this.$http.post('ingredients?projection=inlinedIngredient', tempIngredient, {
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
        this.$http.delete('ingredients/' + this.ingredient.id).then(response => {
          this.visible = false
          this.success(this.$messages.successAction)
          console.log('fire ingredient-edited event')
          this.$events.fire('ingredient-edited', this.ingredient)
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
      this.ingredient = response.data
      this.success(this.$messages.successAction)
      this.refreshNutrientsInformation()
      console.log('fire ingredient-edited event')
      this.$events.fire('ingredient-edited', this.ingredient)
    },
    transformRequest (data, headers) {
      data.foodCategoryCoreType = this.convertEntityToURI(data.foodCategoryCoreType)
      data.foodCategorySubType = this.convertEntityToURI(data.foodCategorySubType)
      data.meatCategoryType = data.meatCategoryType != null ? this.convertEntityToURI(data.meatCategoryType) : null
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      return JSON.stringify(data)
    },
    transformNutrientsRequest (data, headers) {
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      return JSON.stringify(data)
    },
    updateMineral (mineral) {
      let _self = this
      mineral.nutrientsInformation = this.ingredient.nutrientsInformation
      this.$http.patch('minerals/' + mineral.id, mineral, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshMinerals()
        this.success(this.$messages.successAction)
      })
    },
    updateVitamin (vitamin) {
      let _self = this
      vitamin.nutrientsInformation = this.ingredient.nutrientsInformation
      this.$http.patch('vitamins/' + vitamin.id, vitamin, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshVitamins()
        this.success(this.$messages.successAction)
      })
    },
    updateLipid (lipid) {
      let _self = this
      lipid.nutrientsInformation = this.ingredient.nutrientsInformation
      this.$http.patch('lipids/' + lipid.id, lipid, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshLipids()
        this.success(this.$messages.successAction)
      })
    },
    updateProximate (proximate) {
      let _self = this
      proximate.nutrientsInformation = this.ingredient.nutrientsInformation
      this.$http.patch('proximates/' + proximate.id, proximate, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProximates()
        this.success(this.$messages.successAction)
      })
    },
    updateOtherNutrient (otherNutrient) {
      let _self = this
      this.$http.patch('otherNutrients/' + otherNutrient.id, otherNutrient, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshOtherNutrients()
        this.success(this.$messages.successAction)
      })
    },
    foodCategoryCoreTypeChanged (foodCategoryCoreType) {
      if (foodCategoryCoreType == null) {
        this.ingredient.foodCategorySubType = null
        this.foodCategorySubTypes = []
      }
      this.refreshFoodCategorySubTypes(foodCategoryCoreType)
    },
    foodCategorySubTypeChanged (foodCategorySubType) {
      if (foodCategorySubType == null) {
        this.ingredient.meatCategoryType = null
        this.meatCategoryTypes = []
      }
      this.refreshMeatCategoryTypes(foodCategorySubType)
    }
  }
}

function initIngredient () {
  return {
    id: null,
    name: '',
    photoPath: null,
    nutrientsInformation: null,
    foodCategoryCoreType: null,
    foodCategorySubType: null,
    meatCategoryType: null,
    quantified: false
  }
}
