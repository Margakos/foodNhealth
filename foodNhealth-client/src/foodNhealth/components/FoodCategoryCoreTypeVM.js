export default {
  name: 'foodCategoryCoreType',
  components: {},
  data: function () {
    return {
      visible: false,
      foodCategoryCoreType: initFoodCategoryCoreType(),
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
    console.log('FoodCategoryCoreType created')
  },
  mounted () {
    this.$events.$on('edit-foodCategoryCoreType', eventData => this.onEditFoodCategoryCoreType(eventData))
    console.log('FoodCategoryCoreType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-foodCategoryCoreType')
    console.log('FoodCategoryCoreType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.foodCategoryCoreType.id != null
    }
  },
  methods: {
    onEditFoodCategoryCoreType (eventData) {
      console.log('Edit FoodCategoryCoreType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('foodCategoryCoreTypes/' + eventData).then(response => {
          this.foodCategoryCoreType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.foodCategoryCoreType = initFoodCategoryCoreType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.foodCategoryCoreType.id != null) {
          // existing foodCategoryCoreType, update
          this.$http.patch('foodCategoryCoreTypes/' + this.foodCategoryCoreType.id, this.foodCategoryCoreType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new foodCategoryCoreType, create
          this.$http.post('foodCategoryCoreTypes', this.foodCategoryCoreType)
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
      console.log('fire foodCategoryCoreType-edited event')
      this.$events.fire('foodCategoryCoreType-edited', this.foodCategoryCoreType)
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
        // delete foodCategoryCoreType
        this.$http.delete('foodCategoryCoreTypes/' + this.foodCategoryCoreType.id)
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

function initFoodCategoryCoreType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
