export default {
  name: 'vitaminType',
  components: {},
  data: function () {
    return {
      visible: false,
      vitaminType: initVitaminType(),
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
    console.log('VitaminType created')
  },
  mounted () {
    this.$events.$on('edit-vitaminType', eventData => this.onEditVitaminType(eventData))
    console.log('VitaminType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-vitaminType')
    console.log('VitaminType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.vitaminType.id != null
    }
  },
  methods: {
    onEditVitaminType (eventData) {
      console.log('Edit VitaminType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('vitaminTypes/' + eventData).then(response => {
          this.vitaminType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.vitaminType = initVitaminType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.vitaminType.id != null) {
          // existing vitaminType, update
          this.$http.patch('vitaminTypes/' + this.vitaminType.id, this.vitaminType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new vitaminType, create
          this.$http.post('vitaminTypes', this.vitaminType)
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
      console.log('fire vitaminType-edited event')
      this.$events.fire('vitaminType-edited', this.vitaminType)
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
        // delete vitaminType
        this.$http.delete('vitaminTypes/' + this.vitaminType.id)
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

function initVitaminType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
