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
      insertionMode: false, // this become true when nutritionist add a client by find method
      addNewClient: false, // this become true when nutritionist add a new Client in the system
      showPassword: false,
      password: null,
      clients: [],
      // Preference
      preference: initPreference(),
      dislikedIngredients: [],
      allergies: [],
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
          required: true,
          email: true,
          max: 255
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
        },
        // Preference
        dislikedIngredients: {
          required: false
        },
        allergies: {
          required: false
        },
        weightPerWeek: {
          required: false,
          min_value: 0
        }
      },
      genderOptions: [
        {
          value: 'MALE',
          text: this.getMessage('MALE')
        },
        {
          value: 'FEMALE',
          text: this.getMessage('FEMALE')
        }
      ]
    }
  },
  created () {
    Promise.all([this.getAllergies()]).then(([allergies]) => {
      this.allergies = allergies.data._embedded.allergies
    })
    this.searchClients = debounce(this.searchClients, Vue.prototype.$delay)
    this.searchIngredients = debounce(this.searchIngredients, Vue.prototype.$delay)
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
    refreshPreference () { // TODO there are no _links in client
      this.$http.get(this.client._links.preference.href + '?projection=inlinedPreference').then(response => {
        this.preference = response.data
      })
    },
    onEditClient (eventData) {
      console.log('Edit Client:' + eventData)
      this.invalidate()
      this.addNewClient = eventData.addNew !== null ? eventData.addNew : false
      if (eventData.item != null) {
        this.$http.get('clients/' + eventData.item + '?projection=clientSafe').then(response => {
          this.client = response.data
          this.refreshPreference()
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
      this.$validator.validateAll('generalForm').then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.client.email == null || this.client.email === '') {
          _self.$validator.errors.add({field: 'client', msg: _self.$messages.required, scope: 'generalForm'})
          return
        }
        let tempClient = Object.assign({}, this.client)
        tempClient.preference = initPreference()
        this.$http.post('nutritionists/addClient?newClient=' + this.addNewClient + '&projection=clientSafe', tempClient).then(response => {
          this.insertionMode = false
          this.handleSuccess(response)
        }).catch(e => {
          if (e.response && e.response.status === 409) {
            this.error(this.$messages.duplicateClient)
          }
        })
      })
    },
    savePreference () {
      let _self = this
      this.$validator.validateAll('preferenceForm').then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        let tempClient = Object.assign({}, this.preference)
        this.$http.patch('preferences/' + this.preference.id + '?projection=inlinedPreference', tempClient, {
          transformRequest: [function (data, headers) {
            return _self.transformPreferenceRequest(data, headers)
          }]
        }).then(response => {
          this.insertionMode = false
          this.handlePreferenceSuccess(response)
        })
      })
    },
    transformPreferenceRequest (data, headers) {
      data.dislikedIngredients = this.convertEntitiesToURIs(data.dislikedIngredients)
      data.allergies = this.convertEntitiesToURIs(data.allergies)
      return JSON.stringify(data)
    },
    cancel () {
      this.visible = false
    },
    cancelShowPassword () {
      this.showPassword = false
      this.password = null
    },
    handleSuccess (response) {
      this.client = response.data
      if (this.client._links.preference.href.split('{').length === 2) {
        this.client._links.preference.href = this.client._links.preference.href.split('{')[0]
      }
      if (this.addNewClient) {
        this.showPassword = true
        this.password = this.client.password
        this.addNewClient = false
      }
      this.refreshPreference()
      this.success(this.$messages.successAction)
      console.log('fire client-edited event')
      this.$events.fire('client-edited', this.client)
    },
    handlePreferenceSuccess (response) {
      this.preference = response.data
      this.success(this.$messages.successAction)
      console.log('fire client-edited event')
      this.$events.fire('client-edited', this.client)
    },
    handleError (e) {
      console.log(e)
      this.error(this.$messages.errorAction)
    },
    confirmRemove () {
      let _self = this
      this.yesNoDialogue(this.$messages.confirmAction, this.$messages.confirmActionTitle, function () {
        _self.$http.delete('nutritionists/removeClient?email=' + _self.client.email).then(response => {
          _self.visible = false
          _self.success(_self.$messages.successAction)
          console.log('fire client-edited event')
          _self.$events.fire('client-edited', _self.client)
        }).catch(e => _self.handleError(e))
      }, null)
    },
    invalidate () {
      this.$validator.reset().then(() => {
        this.errors.clear('generalForm')
        this.errors.clear('preferenceForm')
      })
      this.preference = initPreference()
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
    searchIngredients (query) {
      if (query.trim() === '') {
        return
      }
      this.isLoading = true
      this.$http.get('ingredients/search/findByQuery', {
        params: {
          query: query.trim(),
          projection: 'inlinedIngredient',
          size: 50
        }
      }).then(response => {
        this.dislikedIngredients = response.data._embedded.ingredients
        this.isLoading = false
      })
    },
    clientCustomLabel (client) {
      return client.email !== '' ? client.firstName + ' ' + client.lastName + ' - ' + client.email : ''
    }
  }
}

function initPreference () {
  return {
    id: null,
    dislikedIngredients: [],
    allergies: [],
    weightPerWeek: 0
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
