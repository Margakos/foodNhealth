export default {
  name: 'recipeCategory',
  components: {},
  data: function () {
    return {
      visible: false,
      recipeCategory: initRecipeCategory(),
      foodCategorySubTypes: [],
      rules: {
        title: {
          required: true,
          max: 255
        },
        foodCategorySubType: {
          required: true
        },
        description: {
          required: false,
          max: 1000
        }
      }
    }
  },
  created () {
    console.log('RecipeCategory created')
  },
  mounted () {
    this.$events.$on('edit-recipeCategory', eventData => this.onEditRecipeCategory(eventData))
    console.log('RecipeCategory mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-recipeCategory')
    console.log('RecipeCategory destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.recipeCategory.id != null
    }
  },
  methods: {
    onEditRecipeCategory (eventData) {
      console.log('Edit RecipeCategory:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('recipeCategories/' + eventData).then(response => {
          this.recipeCategory = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.recipeCategory = initRecipeCategory()
        this.visible = true
      }
    },
    save () {
      let _self = this
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.recipeCategory.id != null) {
          // existing recipeCategory, update
          this.$http.patch('recipeCategories/' + this.recipeCategory.id, this.recipeCategory, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new recipeCategory, create
          this.$http.post('recipeCategories', this.recipeCategory, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        }
      })
    },
    transformRequest (data, headers) {
      return JSON.stringify(data)
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.success(this.$messages.successAction)
      this.visible = false
      console.log('fire recipeCategory-edited event')
      this.$events.fire('recipeCategory-edited', this.recipeCategory)
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
        // delete recipeCategory
        this.$http.delete('recipeCategories/' + this.recipeCategory.id)
          .then(response => this.handleSuccess(response))
          .catch(e => this.handleError(e))
      })
    },
    invalidate () {
      this.$validator.reset().then(() => {
        this.errors.clear()
      })
    }
  }
}

function initRecipeCategory () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
