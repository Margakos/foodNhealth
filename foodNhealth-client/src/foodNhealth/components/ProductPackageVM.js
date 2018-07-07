import constants from '@/foodNhealth/constants'

export default {
  name: 'productPackage',
  components: {
    constants
  },
  data: function () {
    return {
      visible: false,
      productPackage: initProductPackage(),
      supermarkets: [],
      quantified: false,
      rules: {
        title: {
          required: true
        },
        quantity: {
          required: true,
          min_value: 0
        },
        numPieces: {
          required: true,
          min_value: 0
        },
        price: {
          required: true,
          min_value: 0,
          max_value: this.$maxAmount
        },
        supermarket: {
          required: false
        }
      }
    }
  },
  created () {
    Promise.all([this.getSupermarkets()]).then(([supermarkets]) => {
      this.supermarkets = supermarkets.data._embedded.supermarkets
    })
    console.log('ProductPackage created')
  },
  mounted () {
    this.$events.$on('edit-productPackage', eventData => this.onEditProductPackage(eventData))
    console.log('ProductPackage mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-productPackage')
    console.log('ProductPackage destroyed')
  },
  computed: {
    isDeletable: function () {
      return this.productPackage.id !== null
    }
  },
  methods: {
    getProductPackage (eventData) {
      return this.$http.get('productPackages/' + eventData.id + '?projection=inlinedProductPackage', {
        transformResponse: [function (data) {
          let productPackage = JSON.parse(data)
          productPackage.price = productPackage.price == null ? 0 : productPackage.price
          return productPackage
        }]
      })
    },
    onEditProductPackage (eventData) {
      // always clear upload related fields
      this.$validator.reset().then(() => {
        this.errors.clear('generalForm')
      })
      this.quantified = eventData.product.ingredient.quantified
      if (eventData.id != null) {
        this.getProductPackage(eventData).then(response => {
          this.productPackage = response.data
          this.visible = true
        })
      } else {
        Object.assign(this.$data.productPackage, initProductPackage())
        this.productPackage.product = eventData != null ? eventData.product : null
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
        if (this.productPackage.quantity === 0) {
          this.$validator.errors.add({field: 'quantity', msg: this.$messages.fieldMoreThanZero, scope: 'generalForm'})
          return
        }
        if (this.quantified && this.productPackage.numPieces === 0) {
          this.$validator.errors.add({field: 'numPieces', msg: this.$messages.fieldMoreThanZero, scope: 'generalForm'})
          return
        }
        if (this.productPackage.price === 0) {
          this.$validator.errors.add({field: 'price', msg: this.$messages.fieldMoreThanZero, scope: 'generalForm'})
          return
        }
        let tempProductPackage = Object.assign({}, _self.productPackage)
        if (_self.productPackage.id !== null) {
          this.$http.patch('productPackages/' + this.productPackage.id + '?projection=inlinedProductPackage', tempProductPackage, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          this.$http.post('productPackages?projection=inlinedProductPackage', tempProductPackage, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }],
            transformResponse: function (data) {
              let productPackage = JSON.parse(data)
              productPackage.price = productPackage.price == null ? 0 : productPackage.price
              return productPackage
            }
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        }
      })
    },
    transformRequest (data, headers) {
      data.product = this.convertEntityToURI(data.product)
      data.supermarket = this.convertEntityToURI(data.supermarket)
      return JSON.stringify(data)
    },
    hide () {
      this.$events.fire('productPackage-edited-finish')
    },
    cancel () {
      this.visible = false
    },
    handleSuccess (response) {
      this.success(this.$messages.successAction)
      console.log('fire productPackage-edited event')
      this.$events.fire('productPackage-edited', this.productPackage)
      this.visible = false
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
        // delete objection type
        this.$http.delete('productPackages/' + this.productPackage.id).then(response => {
          this.visible = false
          this.success(this.$messages.successAction)
          console.log('fire productPackage-edited event')
          this.$events.fire('productPackage-edited', this.productPackage)
        })
          .catch(e => this.handleError(e))
      })
    }
  }
}

function initProductPackage () {
  return {
    id: null,
    title: '',
    product: null,
    quantity: 0,
    numPieces: 0,
    price: 0,
    supermarket: null
  }
}
