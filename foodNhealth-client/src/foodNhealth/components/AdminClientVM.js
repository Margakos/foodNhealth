export default {
  name: 'adminClient',
  components: {},
  data: function () {
    return {
      visible: false,
      client: initClient(),
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
    console.log('Client created')
  },
  mounted () {
    this.$events.$on('edit-adminClient', eventData => this.onEditClient(eventData))
    console.log('Client mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-adminClient')
    console.log('Client destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.client.id != null && this.$auth.check('Lookup_D')
    }
  },
  methods: {
    onEditClient (eventData) {
      console.log('Edit Client:' + eventData)
      if (eventData != null) {
        this.$http.get('clients/' + eventData + '?projection=clientSafe').then(response => {
          this.client = response.data
          this.visible = true
        })
      } else {
        Object.assign(this.$data.client, initClient())
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
        if (this.client.id != null) {
          // existing client, update
          this.$http.patch('clients/' + this.client.id + '?projection=clientSafe', this.client, {
            // transform the selected roles into URIs, before sending
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data)
            }]
          }).then(response => {
            if (this.$auth.user().id === this.client.id) {
              this.warning(this.$messages.warningUserChanged)
              this.logout()
            } else {
              this.handleSuccess(response)
            }
          })
            .catch(e => this.handleError(e))
        } else {
          // new client, create
          this.$http.post('clients?projection=clientSafe', this.client, {
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
      console.log('fire adminClient-edited event')
      this.$events.fire('adminClient-edited', this.client)
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
        // delete client
        this.$http.delete('clients/' + this.client.id).then(response => this.handleSuccess(response))
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

function initClient () {
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
