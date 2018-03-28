import ProductPackage from '@/foodNhealth/components/ProductPackage'

export default {
  name: 'product',
  components: {
    ProductPackage
  },
  data: function () {
    return {
      visible: false,
      product: initProduct(),
      productMinerals: [],
      productMineralTypes: [],
      productVitamins: [],
      productVitaminTypes: [],
      productLipids: [],
      productLipidTypes: [],
      productProximates: [],
      productProximateTypes: [],
      productOtherNutrients: [],
      productOtherNutrientTypes: [],
      productPackages: [],
      ingredients: [],
      rules: {
        name: {
          required: true,
          max: 255
        },
        ingredient: {
          required: true
        },
        quantity: {
          required: true,
          min_value: 0
        }
      },
      productMineralsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'productMineralType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ],
      productVitaminsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'productVitaminType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ],
      productLipidsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'productLipidType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ],
      productProximatesFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'productProximateType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ],
      productOtherNutrientsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'productOtherNutrientType',
          label: 'Τίτλος'
        },
        {
          key: 'quantity',
          label: ' Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'actions',
          label: 'Ενέργειες',
          thClass: 'text-right',
          tdClass: 'text-right'
        }
      ],
      productPackagesFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'title',
          label: 'Ονομασία'
        },
        {
          key: 'quantity',
          label: 'Ποσότητα',
          callback: 'formatGrams'
        },
        {
          key: 'price',
          label: 'Τιμή',
          callback: 'formatAmount'
        },
        {
          key: 'supermarket',
          label: ' Supermarket'
        }
      ]
    }
  },
  created () {
    Promise.all([this.getIngredients()]).then(([ingredients]) => {
      this.ingredients = ingredients.data._embedded.ingredients
    })
    console.log('Product created')
  },
  mounted () {
    this.$events.$on('edit-product', eventData => this.onEditProduct(eventData))
    this.$events.$on('productPackage-edited', eventData => this.onProductPackageEdited(eventData))
    this.$events.$on('productPackage-edited-finish', eventData => this.onProductPackageEditedFinish())
    console.log('Product mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-product')
    this.$events.$off('productPackage-product')
    this.$events.$off('productPackage-edited-finish')
    console.log('Product destroyed')
  },
  computed: {
    isDeletable () {
      return this.product.id != null
    }
  },
  methods: {
    refreshNutrientsInformation () {
      this.refreshProductMinerals()
      this.refreshProductVitamins()
      this.refreshProductLipids()
      this.refreshProductProximates()
      this.refreshProductOtherNutrients()
    },
    refreshProductMinerals () {
      let _self = this
      this.$http.get(this.product.productNutrientsInformation._links.productMinerals.href + '?projection=inlinedMineral').then(response => {
        this.productMinerals = response.data._embedded.productMinerals
        this.productMinerals.forEach(function (productMineral, index) {
          _self.productMineralTypes.push(productMineral.productMineralType)
        })
      })
    },
    refreshProductVitamins () {
      let _self = this
      this.$http.get(this.product.productNutrientsInformation._links.productVitamins.href + '?projection=inlinedVitamin').then(response => {
        this.productVitamins = response.data._embedded.productVitamins
        this.productVitamins.forEach(function (productVitamin, index) {
          _self.productVitaminTypes.push(productVitamin.productVitaminType)
        })
      })
    },
    refreshProductLipids () {
      let _self = this
      this.$http.get(this.product.productNutrientsInformation._links.productLipids.href + '?projection=inlinedLipid').then(response => {
        this.productLipids = response.data._embedded.productLipids
        this.productLipids.forEach(function (productLipid, index) {
          _self.productLipidTypes.push(productLipid.productLipidType)
        })
      })
    },
    refreshProductProximates () {
      let _self = this
      this.$http.get(this.product.productNutrientsInformation._links.productProximates.href + '?projection=inlinedProximate').then(response => {
        this.productProximates = response.data._embedded.productProximates
        this.productProximates.forEach(function (productProximate, index) {
          _self.productProximateTypes.push(productProximate.productProximateType)
        })
      })
    },
    refreshProductOtherNutrients () {
      let _self = this
      this.$http.get(this.product.productNutrientsInformation._links.productOtherNutrients.href + '?projection=inlinedOtherNutrient').then(response => {
        this.productOtherNutrients = response.data._embedded.productOtherNutrients
        this.productOtherNutrients.forEach(function (productOtherNutrient, index) {
          _self.productOtherNutrientTypes.push(productOtherNutrient.productOtherNutrientType)
        })
      })
    },
    refreshProductPackages () {
      this.$http.get(this.product._links.productPackages.href + '?projection=inlinedProductPackage').then(response => {
        this.productPackages = response.data._embedded.productPackages
      })
    },
    onEditProduct (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('products/' + eventData + '?projection=inlinedProduct').then(response => {
          this.product = response.data
          this.refreshNutrientsInformation()
          this.refreshProductPackages()
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        // Add new Product
        Object.assign(this.$data.product, initProduct())
        this.visible = true
      }
    },
    save () {
      this.$validator.validateAll('generalForm').then((result) => {
        if (!result) {
          return
        }
        let _self = this
        let tempProduct = Object.assign({}, this.product)
        if (this.product.id != null) {
          // existing product , update
          this.$http.patch('products/' + this.product.id + '?projection=inlinedProduct', tempProduct, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => this.handleSuccess(response))
            .catch(e => this.handleError(e))
        } else {
          this.$http.post('products?projection=inlinedProduct', tempProduct, {
            transformRequest: [function (data, headers) {
              return _self.transformRequest(data, headers)
            }]
          }).then(response => {
            this.handleSuccess(response)
          }).catch(e => this.handleError(e))
        }
      })
    },
    cancel () {
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
        // delete commitee
        this.$http.delete('products/' + this.product.id).then(response => {
          this.visible = false
          this.success(this.$messages.successAction)
          console.log('fire product-edited event')
          this.$events.fire('product-edited', this.product)
        })
          .catch(e => {
            // only 409 errors should end up here
            this.error(this.$messages.errorDeleteDependencies)
          })
      })
    },
    invalidateAll () {
      this.$validator.reset().then(() => {
        this.errors.clear('generalForm')
        this.errors.clear('productOtherNutrientsForm')
      })
    },
    handleSuccess (response) {
      this.product = response.data
      this.success(this.$messages.successAction)
      this.refreshNutrientsInformation()
      console.log('fire product-edited event')
      this.$events.fire('product-edited', this.product)
    },
    transformRequest (data, headers) {
      data.ingredient = this.convertEntityToURI(data.ingredient)
      data.productNutrientsInformation = this.convertEntityToURI(data.productNutrientsInformation)
      return JSON.stringify(data)
    },
    transformNutrientsRequest (data, headers) {
      data.productNutrientsInformation = this.convertEntityToURI(data.productNutrientsInformation)
      return JSON.stringify(data)
    },
    updateMineral (productMineral) {
      let _self = this
      productMineral.productNutrientsInformation = this.product.productNutrientsInformation
      this.$http.patch('productMinerals/' + productMineral.id, productMineral, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProductMinerals()
        this.success(this.$messages.successAction)
      })
    },
    updateVitamin (productVitamin) {
      let _self = this
      productVitamin.productNutrientsInformation = this.product.productNutrientsInformation
      this.$http.patch('productVitamins/' + productVitamin.id, productVitamin, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProductVitamins()
        this.success(this.$messages.successAction)
      })
    },
    updateLipid (productLipid) {
      let _self = this
      productLipid.productNutrientsInformation = this.product.productNutrientsInformation
      this.$http.patch('productLipids/' + productLipid.id, productLipid, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProductLipids()
        this.success(this.$messages.successAction)
      })
    },
    updateProximate (productProximate) {
      let _self = this
      productProximate.productNutrientsInformation = this.product.productNutrientsInformation
      this.$http.patch('productProximates/' + productProximate.id, productProximate, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProductProximates()
        this.success(this.$messages.successAction)
      })
    },
    updateOtherNutrient (productOtherNutrient) {
      let _self = this
      this.$http.patch('productOtherNutrients/' + productOtherNutrient.id, productOtherNutrient, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.refreshProductOtherNutrients()
        this.success(this.$messages.successAction)
      })
    },
    createProductPackage () {
      console.log('fire edit-productPackage event')
      this.$events.fire('edit-productPackage', {product: this.product})
    },
    editProductPackage (item, index, event) {
      this.$events.fire('edit-productPackage', {id: item.id, product: this.product})
      this.visible = false
    },
    onProductPackageEdited () {
      this.refreshProductPackages()
    },
    onProductPackageEditedFinish () {
      this.visible = true
    }
  }
}

function initProduct () {
  return {
    id: null,
    name: '',
    ingredient: null,
    photoPath: null,
    productNutrientsInformation: null
  }
}
