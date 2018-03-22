export default {
  name: 'ingredient',
  data: function () {
    return {
      visible: false,
      ingredient: initIngredient(),
      rules: {
        name: {
          required: true,
          max: 255
        }
      }
    }
  },
  created () {
    Promise.all([/* get all the Ingredient required attributes (ex foodCategory) */]).then(([foodCategory]) => {
      // this.foodCategory = foodCategory.data._embedded.foodCategories
    }).catch(e => {
      console.log(e)
      this.error(this.$messages.errorLoad)
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
    isDeletable: function () {
      return this.ingredient.id != null
    }
  },
  methods: {
    onEditIngredient (eventData) {
      if (eventData != null) {
        // Edit existing row
        this.$http.get('ingredients/' + eventData + '?projection=inlinedIngredient').then(response => {
          this.ingredient = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        // Add new Ingredient
        Object.assign(this.$data.ingredient, initIngredient())
        // this is the right way to reset the vee-validator
        this.$validator.reset().then(() => {
          this.errors.clear('generalForm')
        })
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
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
          this.$http.post('ingredients' + '?projection=inlinedIngredient', tempIngredient, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => {
            this.handleSuccess(response)
          }).catch(e => this.handleError(e))
        }
      })
    },
    transformRequest (data, headers) {
      console.log(this.ingredientMembers)
      // convert entities to url in order to send them to Spring Data Rest (like below)
      // data.foodCategory = this.convertEntityToURI(data.foodCategory)
      return JSON.stringify(data)
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.ingredient = response.data
      this.ingredient.year = new Date(Date.UTC(this.ingredient.year, 0))
      this.success(this.$messages.successAction)
      console.log('fire ingredient-edited event')
      this.$events.fire('ingredient-edited', this.ingredient)
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
    }
  }
}
function initIngredient () {
  return {
    id: null
  }
}

