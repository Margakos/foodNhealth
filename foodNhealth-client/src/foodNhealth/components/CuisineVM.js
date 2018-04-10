export default {
  name: 'cuisine',
  components: {},
  data: function () {
    return {
      visible: false,
      cuisine: initCuisine(),
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
    console.log('Cuisine created')
  },
  mounted () {
    this.$events.$on('edit-cuisine', eventData => this.onEditCuisine(eventData))
    console.log('Cuisine mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-cuisine')
    console.log('Cuisine destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.cuisine.id != null
    }
  },
  methods: {
    onEditCuisine (eventData) {
      console.log('Edit Cuisine:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('cuisines/' + eventData).then(response => {
          this.cuisine = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.cuisine = initCuisine()
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
        if (this.cuisine.id != null) {
          // existing cuisine, update
          this.$http.patch('cuisines/' + this.cuisine.id, this.cuisine, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new cuisine, create
          this.$http.post('cuisines', this.cuisine, {
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
      console.log('fire cuisine-edited event')
      this.$events.fire('cuisine-edited', this.cuisine)
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
        // delete cuisine
        this.$http.delete('cuisines/' + this.cuisine.id)
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

function initCuisine () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
