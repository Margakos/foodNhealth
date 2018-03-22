export default {
  name: 'otherNutrientType',
  components: {},
  data: function () {
    return {
      visible: false,
      otherNutrientType: initOtherNutrientType(),
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
    console.log('OtherNutrientType created')
  },
  mounted () {
    this.$events.$on('edit-otherNutrientType', eventData => this.onEditOtherNutrientType(eventData))
    console.log('OtherNutrientType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-otherNutrientType')
    console.log('OtherNutrientType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.otherNutrientType.id != null
    }
  },
  methods: {
    onEditOtherNutrientType (eventData) {
      console.log('Edit OtherNutrientType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('otherNutrientTypes/' + eventData).then(response => {
          this.otherNutrientType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.otherNutrientType = initOtherNutrientType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.otherNutrientType.id != null) {
          // existing otherNutrientType, update
          this.$http.patch('otherNutrientTypes/' + this.otherNutrientType.id, this.otherNutrientType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new otherNutrientType, create
          this.$http.post('otherNutrientTypes', this.otherNutrientType)
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
      console.log('fire otherNutrientType-edited event')
      this.$events.fire('otherNutrientType-edited', this.otherNutrientType)
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
        // delete otherNutrientType
        this.$http.delete('otherNutrientTypes/' + this.otherNutrientType.id)
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

function initOtherNutrientType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
