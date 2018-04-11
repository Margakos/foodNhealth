export default {
  name: 'allergy',
  components: {},
  data: function () {
    return {
      visible: false,
      allergy: initAllergy(),
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
    console.log('Allergy created')
  },
  mounted () {
    this.$events.$on('edit-allergy', eventData => this.onEditAllergy(eventData))
    console.log('Allergy mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-allergy')
    console.log('Allergy destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.allergy.id != null
    }
  },
  methods: {
    onEditAllergy (eventData) {
      console.log('Edit Allergy:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('allergies/' + eventData).then(response => {
          this.allergy = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.allergy = initAllergy()
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
        if (this.allergy.id != null) {
          // existing allergy, update
          this.$http.patch('allergies/' + this.allergy.id, this.allergy, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new allergy, create
          this.$http.post('allergies', this.allergy, {
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
      console.log('fire allergy-edited event')
      this.$events.fire('allergy-edited', this.allergy)
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
        // delete allergy
        this.$http.delete('allergies/' + this.allergy.id)
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

function initAllergy () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
