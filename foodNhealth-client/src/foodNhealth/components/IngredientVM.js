const availableFormGrams = 'GRAMS'
const availableFormPieces = 'PIECES'
const availableFormSlices = 'SLICES'

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
      availableForms: [availableFormGrams, availableFormPieces, availableFormSlices],
      rules: {
        name: {
          required: true,
          max: 255
        },
        availableForm: {
          required: true
        },
        mineralType: {
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
      ]
    }
  },
  created () {
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
    isDeletable: function () {
      return this.ingredient.id != null
    }
  },
  methods: {
    refreshMineralTypes () {
      this.$http.get('mineralTypes/search/findNotParticipating?ingredientId=' + this.ingredient.id).then(response => {
        this.mineralTypes = response.data._embedded.mineralTypes
      })
    },
    refreshMinerals () {
      this.$http.get(this.ingredient.nutrientsInformation._links.minerals.href + '?projection=inlinedMineral').then(response => {
        this.minerals = response.data._embedded.minerals
      })
    },
    onEditIngredient (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('ingredients/' + eventData + '?projection=inlinedIngredient').then(response => {
          this.ingredient = response.data
          this.refreshMineralTypes()
          this.refreshMinerals()
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
    }
  }
}

function initIngredient () {
  return {
    id: null,
    name: '',
    availableForm: null,
    nutrientsInformation: null,
    foodCategory: null
  }
}
