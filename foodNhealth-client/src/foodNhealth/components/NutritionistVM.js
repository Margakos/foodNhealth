export default {
  name: 'nutritionist',
  components: {},
  data: function () {
    return {
      visible: false,
      nutritionist: initNutritionist(),
      rules: {
        firstName: {
          required: true,
          max: 255
        },
        lastName: {
          required: true,
          max: 255
        },
        email: {
          required: true,
          email: true,
          max: 255
        },
        password: {
          min: 6,
          max: 20
        },
        isActive: {
          required: true
        },
        comments: {
          max: 255
        },
        gender: {}
      }
    }
  },
  created () {
    console.log('Nutritionist created')
  },
  mounted () {
    this.$events.$on('edit-nutritionist', eventData => this.onEditNutritionist(eventData))
    console.log('Nutritionist mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-nutritionist')
    console.log('Nutritionist destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.nutritionist.id != null && this.$auth.check('Lookup_D')
    }
  },
  methods: {
    onEditNutritionist (eventData) {
      console.log('Edit Nutritionist:' + eventData)
      if (eventData != null) {
        this.$http.get('nutritionists/' + eventData + '?projection=nutritionistSafe').then(response => {
          this.nutritionist = response.data
          this.visible = true
        })
      } else {
        Object.assign(this.$data.nutritionist, initNutritionist())
        this.$validator.reset().then(() => {
          this.errors.clear()
        })
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        let _self = this
        if (this.nutritionist.id != null) {
          // existing nutritionist, update
          this.$http.patch('nutritionists/' + this.nutritionist.id + '?projection=nutritionistSafe', this.nutritionist, {
            // transform the selected roles into URIs, before sending
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data)
            }]
          }).then(response => {
            if (this.$auth.user().id === this.nutritionist.id) {
              this.warning(this.$messages.warningUserChanged)
              this.logout()
            } else {
              this.handleSuccess(response)
            }
          })
            .catch(e => this.handleError(e))
        } else {
          // new nutritionist, create
          this.$http.post('nutritionists?projection=nutritionistSafe', this.nutritionist, {
            // transform the selected roles into URIs, before sending
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        }
      })
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.visible = false
      this.success(this.$messages.successAction)
      console.log('fire nutritionist-edited event')
      this.$events.fire('nutritionist-edited', this.nutritionist)
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
        // delete nutritionist
        this.$http.delete('nutritionists/' + this.nutritionist.id).then(response => this.handleSuccess(response))
          .catch(e => this.handleError(e))
      })
    },
    transformRequest (data) {
      if (data.password == null) {
        delete data.password
      }
      return JSON.stringify(data)
    }
  }
}

function initNutritionist () {
  return {
    id: null,
    firstName: '',
    lastName: '',
    gender: null,
    email: '',
    password: null,
    comments: '',
    isActive: true
  }
}
