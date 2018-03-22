export default {
  name: 'foodCategorySubType',
  components: {},
  data: function () {
    return {
      visible: false,
      foodCategorySubType: initFoodCategorySubType(),
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
    console.log('FoodCategorySubType created')
  },
  mounted () {
    this.$events.$on('edit-foodCategorySubType', eventData => this.onEditFoodCategorySubType(eventData))
    console.log('FoodCategorySubType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-foodCategorySubType')
    console.log('FoodCategorySubType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.foodCategorySubType.id != null
    }
  },
  methods: {
    onEditFoodCategorySubType (eventData) {
      console.log('Edit FoodCategorySubType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('foodCategorySubTypes/' + eventData).then(response => {
          this.foodCategorySubType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.foodCategorySubType = initFoodCategorySubType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.foodCategorySubType.id != null) {
          // existing foodCategorySubType, update
          this.$http.patch('foodCategorySubTypes/' + this.foodCategorySubType.id, this.foodCategorySubType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new foodCategorySubType, create
          this.$http.post('foodCategorySubTypes', this.foodCategorySubType)
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
      console.log('fire foodCategorySubType-edited event')
      this.$events.fire('foodCategorySubType-edited', this.foodCategorySubType)
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
        // delete foodCategorySubType
        this.$http.delete('foodCategorySubTypes/' + this.foodCategorySubType.id)
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

function initFoodCategorySubType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
