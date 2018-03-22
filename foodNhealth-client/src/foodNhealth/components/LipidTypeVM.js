export default {
  name: 'lipidType',
  components: {},
  data: function () {
    return {
      visible: false,
      lipidType: initLipidType(),
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
    console.log('LipidType created')
  },
  mounted () {
    this.$events.$on('edit-lipidType', eventData => this.onEditLipidType(eventData))
    console.log('LipidType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-lipidType')
    console.log('LipidType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.lipidType.id != null
    }
  },
  methods: {
    onEditLipidType (eventData) {
      console.log('Edit LipidType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('lipidTypes/' + eventData).then(response => {
          this.lipidType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.lipidType = initLipidType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.lipidType.id != null) {
          // existing lipidType, update
          this.$http.patch('lipidTypes/' + this.lipidType.id, this.lipidType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new lipidType, create
          this.$http.post('lipidTypes', this.lipidType)
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
      console.log('fire lipidType-edited event')
      this.$events.fire('lipidType-edited', this.lipidType)
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
        // delete lipidType
        this.$http.delete('lipidTypes/' + this.lipidType.id)
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

function initLipidType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
