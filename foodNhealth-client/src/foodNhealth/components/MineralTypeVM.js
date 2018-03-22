export default {
  name: 'mineralType',
  components: {},
  data: function () {
    return {
      visible: false,
      mineralType: initMineralType(),
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
    console.log('MineralType created')
  },
  mounted () {
    this.$events.$on('edit-mineralType', eventData => this.onEditMineralType(eventData))
    console.log('MineralType mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-mineralType')
    console.log('MineralType destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.mineralType.id != null
    }
  },
  methods: {
    onEditMineralType (eventData) {
      console.log('Edit MineralType:' + eventData)
      this.invalidate()
      if (eventData != null) {
        this.$http.get('mineralTypes/' + eventData).then(response => {
          this.mineralType = response.data
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        this.$data.mineralType = initMineralType()
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        if (this.mineralType.id != null) {
          // existing mineralType, update
          this.$http.patch('mineralTypes/' + this.mineralType.id, this.mineralType)
            .then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          // new mineralType, create
          this.$http.post('mineralTypes', this.mineralType)
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
      console.log('fire mineralType-edited event')
      this.$events.fire('mineralType-edited', this.mineralType)
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
        // delete mineralType
        this.$http.delete('mineralTypes/' + this.mineralType.id)
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

function initMineralType () {
  return {
    id: null,
    title: '',
    description: ''
  }
}
