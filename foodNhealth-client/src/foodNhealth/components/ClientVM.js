import Vue from 'vue'
import debounce from 'debounce'

export default {
  name: 'client',
  components: {},
  data: function () {
    return {
      visible: false,
      client: initClient(),
      isLoading: false,
      insertionMode: false,
      clients: [],
      rules: {
        client: {
          required: true
        },
        firstName: {
          required: true
        },
        lastName: {
          required: true
        },
        email: {
          required: true
        },
        address: {
          required: false
        },
        city: {
          required: false
        },
        zipCode: {
          required: false
        },
        gender: {
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
    this.searchClients = debounce(this.searchClients, Vue.prototype.$delay)
  },
  mounted () {
    this.$events.$on('edit-client', eventData => this.onEditClient(eventData))
    console.log('Client mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-client')
    console.log('Client destroyed')
  },
  computed: {
    isDeletable () {
      return !this.insertionMode
    }
  },
  methods: {
    onEditClient (eventData) {
      console.log('Edit Client:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('clients/' + eventData + '?projection=clientSafe').then(response => {
          this.client = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.client = initClient()
        this.insertionMode = true
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
        if (this.client.email == null || this.client.email === '') {
          _self.$validator.errors.add({field: 'client', msg: _self.$messages.required, scope: 'generalForm'})
          return
        }
        let tempClient = Object.assign({}, this.client)
        this.$http.post('nutritionists/addClient', tempClient, {
          transformRequest: [function (data, headers) {
            return _self.transformRequest(data, headers)
          }]
        }).then(response => {
          this.insertionMode = false
          this.handleSuccess(response)
        })
          .catch(e => {
            if (e.response && e.response.status === 409) {
              this.error(this.$messages.duplicateClient)
            }
          })
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
      console.log('fire client-edited event')
      this.$events.fire('client-edited', this.client)
    },
    handleError (e) {
      console.log(e)
      this.error(this.$messages.errorAction)
    },
    confirmDelete () {
      let _self = this
      this.yesNoDialogue(this.$messages.confirmAction, this.$messages.confirmActionTitle, function () {
        _self.$http.delete('nutritionists/removeClient?email=' + _self.client.email).then(response => _self.handleSuccess(response)).catch(e => _self.handleError(e))
      }, null)
    },
    invalidate () {
      this.$validator.reset().then(() => {
        this.errors.clear('generalForm')
      })
    },
    searchClients (query) {
      if (query.trim() === '') {
        return
      }
      this.isLoading = true
      this.$http.get('clients/search/searchByQuery', {
        params: {
          query: query.trim(),
          projection: 'clientSafe',
          size: 50
        }
      }).then(response => {
        this.clients = response.data._embedded.clients
        this.isLoading = false
      })
    },
    clientCustomLabel (client) {
      return client.email !== '' ? client.firstName + ' ' + client.lastName + ' - ' + client.email : ''
    }
  }
}

function initClient () {
  return {
    id: null,
    firstName: '',
    lastName: '',
    email: '',
    address: '',
    city: '',
    zipCode: '',
    gender: ''
  }
}
