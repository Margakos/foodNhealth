export default {
  name: 'meatCategoryType',
  components: {},
  data: function () {
    return {
      visible: false,
      meatCategoryType: initMeatCategoryType(),
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
    let _self = this
    Promise.all([this.getAllFoodCategorySubTypes()]).then(([foodCategorySubTypes]) => {
      foodCategorySubTypes.data._embedded.foodCategorySubTypes.forEach(function (foodCategorySubType, index) {
        if (foodCategorySubType.title.includes('Fat')) {
          _self.foodCategorySubTypes.push(foodCategorySubType)
        }
      })
    })
    console.log('MeatCategoryType created')
  },
  mounted () {
    this.$events.$on('edit-meatCategoryType', eventData => this.onEditMeatCategoryType(eventData))
    console.log('MeatCategoryType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-meatCategoryType')
    console.log('MeatCategoryType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.meatCategoryType.id != null
    }
  },
  methods: {
    onEditMeatCategoryType (eventData) {
      console.log('Edit MeatCategoryType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('meatCategoryTypes/' + eventData).then(response => {
          this.meatCategoryType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.meatCategoryType = initMeatCategoryType()
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
        if (this.meatCategoryType.id != null) {
          // existing meatCategoryType, update
          this.$http.patch('meatCategoryTypes/' + this.meatCategoryType.id, this.meatCategoryType, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new meatCategoryType, create
          this.$http.post('meatCategoryTypes', this.meatCategoryType, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        }
      })
    },
    transformRequest (data, headers) {
      data.foodCategorySubType = this.convertEntityToURI(data.foodCategorySubType)
      return JSON.stringify(data)
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.success(this.$messages.successAction)
      this.visible = false
      console.log('fire meatCategoryType-edited event')
      this.$events.fire('meatCategoryType-edited', this.meatCategoryType)
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
        // delete meatCategoryType
        this.$http.delete('meatCategoryTypes/' + this.meatCategoryType.id)
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

function initMeatCategoryType () {
  return {
    id: null,
    title: '',
    description: '',
    foodCategorySubType: null
  }
}
