export default {
  name: 'proximateType',
  components: {},
  data: function () {
    return {
      visible: false,
      proximateType: initProximateType(),
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
    console.log('ProximateType created')
  },
  mounted () {
    this.$events.$on('edit-proximateType', eventData => this.onEditProximateType(eventData))
    console.log('ProximateType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-proximateType')
    console.log('ProximateType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.proximateType.id != null
    }
  },
  methods: {
    onEditProximateType (eventData) {
      console.log('Edit ProximateType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('proximateTypes/' + eventData).then(response => {
          this.proximateType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.proximateType = initProximateType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.proximateType.id != null) {
          // existing proximateType, update
          this.$http.patch('proximateTypes/' + this.proximateType.id, this.proximateType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new proximateType, create
          this.$http.post('proximateTypes', this.proximateType)
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
      console.log('fire proximateType-edited event')
      this.$events.fire('proximateType-edited', this.proximateType)
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
        // delete proximateType
        this.$http.delete('proximateTypes/' + this.proximateType.id)
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

function initProximateType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
