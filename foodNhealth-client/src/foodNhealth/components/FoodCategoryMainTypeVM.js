export default {
  name: 'foodCategoryMainType',
  components: {},
  data: function () {
    return {
      visible: false,
      foodCategoryMainType: initFoodCategoryMainType(),
      rules: {
        title: {
          required: true,
          max: 255
        },
        description: {
          required: false,
          max: 1000
        }
      }
    }
  },
  created () {
    console.log('FoodCategoryMainType created')
  },
  mounted () {
    this.$events.$on('edit-foodCategoryMainType', eventData => this.onEditFoodCategoryMainType(eventData))
    console.log('FoodCategoryMainType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-foodCategoryMainType')
    console.log('FoodCategoryMainType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.foodCategoryMainType.id != null
    }
  },
  methods: {
    onEditFoodCategoryMainType (eventData) {
      console.log('Edit FoodCategoryMainType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('foodCategoryMainTypes/' + eventData).then(response => {
          this.foodCategoryMainType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.foodCategoryMainType = initFoodCategoryMainType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.foodCategoryMainType.id != null) {
          // existing foodCategoryMainType, update
          this.$http.patch('foodCategoryMainTypes/' + this.foodCategoryMainType.id, this.foodCategoryMainType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new foodCategoryMainType, create
          this.$http.post('foodCategoryMainTypes', this.foodCategoryMainType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        }
      })
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.success(this.$messages.successAction)
      this.visible = false
      console.log('fire foodCategoryMainType-edited event')
      this.$events.fire('foodCategoryMainType-edited', this.foodCategoryMainType)
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
        // delete foodCategoryMainType
        this.$http.delete('foodCategoryMainTypes/' + this.foodCategoryMainType.id)
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

function initFoodCategoryMainType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
