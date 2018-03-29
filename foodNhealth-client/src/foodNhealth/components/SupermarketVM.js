export default {
  name: 'supermarket',
  components: {},
  data: function () {
    return {
      visible: false,
      supermarket: initSupermarket(),
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
    console.log('Supermarket created')
  },
  mounted () {
    this.$events.$on('edit-supermarket', eventData => this.onEditSupermarket(eventData))
    console.log('Supermarket mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-supermarket')
    console.log('Supermarket destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.supermarket.id != null
    }
  },
  methods: {
    onEditSupermarket (eventData) {
      console.log('Edit Supermarket:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('supermarkets/' + eventData).then(response => {
          this.supermarket = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.supermarket = initSupermarket()
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
        if (this.supermarket.id != null) {
          // existing supermarket, update
          this.$http.patch('supermarkets/' + this.supermarket.id, this.supermarket, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new supermarket, create
          this.$http.post('supermarkets', this.supermarket, {
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
      console.log('fire supermarket-edited event')
      this.$events.fire('supermarket-edited', this.supermarket)
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
        // delete supermarket
        this.$http.delete('supermarkets/' + this.supermarket.id)
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

function initSupermarket () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
