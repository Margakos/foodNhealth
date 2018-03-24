export default {
  name: 'person',
  components: {},
  data: function () {
    return {
      visible: false,
      person: initPerson(),
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
    console.log('Person created')
  },
  mounted () {
    this.$events.$on('edit-person', eventData => this.onEditPerson(eventData))
    console.log('Person mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-person')
    console.log('Person destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.person.id != null && this.$auth.check('Lookup_D')
    }
  },
  methods: {
    onEditPerson (eventData) {
      console.log('Edit Person:' + eventData)
      if (eventData != null) {
        this.$http.get('persons/' + eventData + '?projection=loginPerson').then(response => {
          this.person = response.data
          this.visible = true
        })
      } else {
        Object.assign(this.$data.person, initPerson())
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
        if (this.person.id != null) {
          // existing person, update
          this.$http.patch('persons/' + this.person.id + '?projection=loginPerson', this.person, {
            // transform the selected roles into URIs, before sending
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data)
            }]
          }).then(response => {
            if (this.$auth.user().id === this.person.id) {
              this.warning(this.$messages.warningUserChanged)
              this.logout()
            } else {
              this.handleSuccess(response)
            }
          })
            .catch(e => this.handleError(e))
        } else {
          // new person, create
          this.$http.post('persons?projection=loginPerson', this.person, {
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
      console.log('fire person-edited event')
      this.$events.fire('person-edited', this.person)
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
        // delete person
        this.$http.delete('persons/' + this.person.id).then(response => this.handleSuccess(response))
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

function initPerson () {
  return {
    id: null,
    firstName: '',
    lastName: '',
    gender: null,
    email: '',
    password: null,
    comments: '',
    isActive: true,
    roles: null,
    department: null,
    accessibleDepartments: []
  }
}
