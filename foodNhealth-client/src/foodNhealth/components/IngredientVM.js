export default {
  name: 'ingredient',
  data: function () {
    return {
      visible: false,
      ingredient: initIngredient(),
      minerals: [],
      mineralTypes: [],
      mineralType: null,
      mineralQuantity: 0,
      vitamins: [],
      vitaminTypes: [],
      vitaminType: null,
      vitaminQuantity: 0,
      lipids: [],
      lipidTypes: [],
      lipidType: null,
      lipidQuantity: 0,
      proximates: [],
      proximateTypes: [],
      proximateType: null,
      proximateQuantity: 0,
      otherNutrients: [],
      otherNutrientTypes: [],
      otherNutrientType: null,
      otherNutrientQuantity: 0,
      foodCategoryCoreTypes: [],
      foodCategorySubTypes: [],
      meatCategoryTypes: [],
      meatCategoryType: null,
      rules: {
        name: {
          required: true,
          max: 255
        },
        mineralType: {
          required: true
        },
        vitaminType: {
          required: true
        },
        lipidType: {
          required: true
        },
        proximateType: {
          required: true
        },
        otherNutrientType: {
          required: true
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
          min_value: 0.000000000001
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
    Promise.all([this.getFoodCategoryCoreTypes(), this.getMeatCategoryTypes()])
      .then(([foodCategoryCoreTypes, meatCategoryTypes]) => {
        this.foodCategoryCoreTypes = foodCategoryCoreTypes.data._embedded.foodCategoryCoreTypes
        this.meatCategoryTypes = meatCategoryTypes.data._embedded.meatCategoryTypes
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
      return this.ingredient.foodCategorySubType != null && this.ingredient.foodCategorySubType.title !== 'Κρέας'
    },
    isFoodCategorySubTypeDisabled () {
      return this.ingredient.foodCategoryCoreType == null
    }
  },
  methods: {
    refreshNutrientsInformation () {
      this.refreshMineralTypes()
      this.refreshVitaminTypes()
      this.refreshLipidTypes()
      this.refreshProximateTypes()
      this.refreshOtherNutrientTypes()
      this.refreshMinerals()
      this.refreshVitamins()
      this.refreshLipids()
      this.refreshProximates()
      this.refreshOtherNutrients()
    },
    refreshMineralTypes () {
      this.$http.get('mineralTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.mineralTypes = response.data._embedded.mineralTypes
      })
    },
    refreshVitaminTypes () {
      this.$http.get('vitaminTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.vitaminTypes = response.data._embedded.vitaminTypes
      })
    },
    refreshLipidTypes () {
      this.$http.get('lipidTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.lipidTypes = response.data._embedded.lipidTypes
      })
    },
    refreshProximateTypes () {
      this.$http.get('proximateTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.proximateTypes = response.data._embedded.proximateTypes
      })
    },
    refreshOtherNutrientTypes () {
      this.$http.get('otherNutrientTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.otherNutrientTypes = response.data._embedded.otherNutrientTypes
      })
    },
    refreshMinerals () {
      this.$http.get(this.ingredient.nutrientsInformation._links.minerals.href + '?projection=inlinedMineral').then(response => {
        this.minerals = response.data._embedded.minerals
      })
    },
    refreshVitamins () {
      this.$http.get(this.ingredient.nutrientsInformation._links.vitamins.href + '?projection=inlinedVitamin').then(response => {
        this.vitamins = response.data._embedded.vitamins
      })
    },
    refreshLipids () {
      this.$http.get(this.ingredient.nutrientsInformation._links.lipids.href + '?projection=inlinedLipid').then(response => {
        this.lipids = response.data._embedded.lipids
      })
    },
    refreshProximates () {
      this.$http.get(this.ingredient.nutrientsInformation._links.proximates.href + '?projection=inlinedProximate').then(response => {
        this.proximates = response.data._embedded.proximates
      })
    },
    refreshOtherNutrients () {
      this.$http.get(this.ingredient.nutrientsInformation._links.otherNutrients.href + '?projection=inlinedOtherNutrient').then(response => {
        this.otherNutrients = response.data._embedded.otherNutrients
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
            this.refreshMineralTypes()
            this.refreshVitaminTypes()
            this.refreshLipidTypes()
            this.refreshProximateTypes()
            this.refreshOtherNutrientTypes()
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
        // delete commitee
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
        this.errors.clear('mineralsForm')
        this.errors.clear('vitaminsForm')
        this.errors.clear('lipidsForm')
        this.errors.clear('proximatesForm')
        this.errors.clear('otherNutrientsForm')
        this.errors.clear('foodCategoryForm')
      })
    },
    handleSuccess (response) {
      this.ingredient = response.data
      this.success(this.$messages.successAction)
      console.log('fire ingredient-edited event')
      this.$events.fire('ingredient-edited', this.ingredient)
    },
    transformRequest (data, headers) {
      data.foodCategory = this.convertEntityToURI(data.foodCategory)
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      return JSON.stringify(data)
    },
    addMineral () {
      this.$validator.validateAll('mineralsForm').then((result) => {
        if (!result) {
          return
        }
        let mineral = {
          nutrientsInformation: this.ingredient.nutrientsInformation._links.self.href,
          mineralType: this.mineralType._links.self.href,
          quantity: this.mineralQuantity
        }
        this.$http.post('minerals', mineral).then(response => {
          this.mineralType = null
          this.mineralQuantity = 0
          this.refreshMinerals()
          this.refreshMineralTypes()
          this.$validator.reset().then(() => {
            this.errors.clear('mineralsForm')
          })
        })
      })
    },
    addVitamin () {
      this.$validator.validateAll('vitaminsForm').then((result) => {
        if (!result) {
          return
        }
        let vitamin = {
          nutrientsInformation: this.ingredient.nutrientsInformation._links.self.href,
          vitaminType: this.vitaminType._links.self.href,
          quantity: this.vitaminQuantity
        }
        this.$http.post('vitamins', vitamin).then(response => {
          this.vitaminType = null
          this.vitaminQuantity = 0
          this.refreshVitamins()
          this.refreshVitaminTypes()
          this.$validator.reset().then(() => {
            this.errors.clear('vitaminsForm')
          })
        })
      })
    },
    addLipid () {
      this.$validator.validateAll('lipidsForm').then((result) => {
        if (!result) {
          return
        }
        let lipid = {
          nutrientsInformation: this.ingredient.nutrientsInformation._links.self.href,
          lipidType: this.lipidType._links.self.href,
          quantity: this.lipidQuantity
        }
        this.$http.post('lipids', lipid).then(response => {
          this.lipidType = null
          this.lipidQuantity = 0
          this.refreshLipids()
          this.refreshLipidTypes()
          this.$validator.reset().then(() => {
            this.errors.clear('lipidsForm')
          })
        })
      })
    },
    addProximate () {
      this.$validator.validateAll('proximatesForm').then((result) => {
        if (!result) {
          return
        }
        let proximate = {
          nutrientsInformation: this.ingredient.nutrientsInformation._links.self.href,
          proximateType: this.proximateType._links.self.href,
          quantity: this.proximateQuantity
        }
        this.$http.post('proximates', proximate).then(response => {
          this.proximateType = null
          this.proximateQuantity = 0
          this.refreshProximates()
          this.refreshProximateTypes()
          this.$validator.reset().then(() => {
            this.errors.clear('proximatesForm')
          })
        })
      })
    },
    addOtherNutrient () {
      this.$validator.validateAll('otherNutrientsForm').then((result) => {
        if (!result) {
          return
        }
        let otherNutrient = {
          nutrientsInformation: this.ingredient.nutrientsInformation._links.self.href,
          otherNutrientType: this.otherNutrientType._links.self.href,
          quantity: this.otherNutrientQuantity
        }
        this.$http.post('otherNutrients', otherNutrient).then(response => {
          this.otherNutrientType = null
          this.otherNutrientQuantity = 0
          this.refreshOtherNutrients()
          this.refreshOtherNutrientTypes()
          this.$validator.reset().then(() => {
            this.errors.clear('otherNutrientsForm')
          })
        })
      })
    },
    confirmRemoveMineral (data) {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('minerals/' + data.id).then(response => {
          this.refreshMinerals()
          this.refreshMineralTypes()
        })
      }).catch(e => {
        // confirm dialog cancelled
      })
    },
    confirmRemoveVitamin (data) {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('vitamins/' + data.id).then(response => {
          this.refreshVitamins()
          this.refreshVitaminTypes()
        })
      }).catch(e => {
        // confirm dialog cancelled
      })
    },
    confirmRemoveLipid (data) {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('lipids/' + data.id).then(response => {
          this.refreshLipids()
          this.refreshLipidTypes()
        })
      }).catch(e => {
        // confirm dialog cancelled
      })
    },
    confirmRemoveProximate (data) {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('proximates/' + data.id).then(response => {
          this.refreshProximates()
          this.refreshProximateTypes()
        })
      }).catch(e => {
        // confirm dialog cancelled
      })
    },
    confirmRemoveOtherNutrient (data) {
      this.$confirm(this.$messages.confirmAction, this.$messages.confirmActionTitle, {
        confirmButtonText: this.$messages.yes,
        cancelButtonText: this.$messages.no,
        cancelButtonClass: 'btn btn-warning',
        confirmButtonClass: 'btn btn-danger',
        closeOnClickModal: false,
        closeOnPressEscape: false,
        type: 'warning'
      }).then(() => {
        this.$http.delete('otherNutrients/' + data.id).then(response => {
          this.refreshOtherNutrients()
          this.refreshOtherNutrientTypes()
        })
      }).catch(e => {
        // confirm dialog cancelled
      })
    },
    foodCategoryCoreTypeChanged (foodCategoryCoreType) {
      if (foodCategoryCoreType == null) {
        this.ingredient.foodCategorySubType = null
        this.foodCategorySubTypes = []
      }
      this.getFoodCategorySubTypes(foodCategoryCoreType.id)
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
    meatCategoryType: null
  }
}
