import ProductPackage from '@/foodNhealth/components/ProductPackage'
import constants from '@/foodNhealth/constants'

export default {
  name: 'product',
  components: {
    ProductPackage,
    constants
  },
  data: function () {
    return {
      visible: false,
      product: initProduct(),
      minerals: [],
      mineralTypes: [],
      vitamins: [],
      vitaminTypes: [],
      lipids: [],
      lipidTypes: [],
      proximates: [],
      proximateTypes: [],
      otherNutrients: [],
      otherNutrientTypes: [],
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
      mineralsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'mineralType',
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
      vitaminsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'vitaminType',
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
      lipidsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'lipidType',
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
      proximatesFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'proximateType',
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
      otherNutrientsFields: [
        {
          key: 'index',
          label: '#'
        },
        {
          key: 'otherNutrientType',
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
          key: 'numPieces',
          label: 'Κομμάτια'
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
      this.refreshMinerals()
      this.refreshVitamins()
      this.refreshLipids()
      this.refreshProximates()
      this.refreshOtherNutrients()
    },
    refreshMinerals () {
      let _self = this
      this.$http.get(this.product.nutrientsInformation._links.minerals.href + '?projection=inlinedMineral').then(response => {
        this.minerals = response.data._embedded.minerals
        this.minerals.forEach(function (mineral, index) {
          _self.mineralTypes.push(mineral.mineralType)
        })
      })
    },
    refreshVitamins () {
      let _self = this
      this.$http.get(this.product.nutrientsInformation._links.vitamins.href + '?projection=inlinedVitamin').then(response => {
        this.vitamins = response.data._embedded.vitamins
        this.vitamins.forEach(function (vitamin, index) {
          _self.vitaminTypes.push(vitamin.vitaminType)
        })
      })
    },
    refreshLipids () {
      let _self = this
      this.$http.get(this.product.nutrientsInformation._links.lipids.href + '?projection=inlinedLipid').then(response => {
        this.lipids = response.data._embedded.lipids
        this.lipids.forEach(function (lipid, index) {
          _self.lipidTypes.push(lipid.lipidType)
        })
      })
    },
    refreshProximates () {
      let _self = this
      this.$http.get(this.product.nutrientsInformation._links.proximates.href + '?projection=inlinedProximate').then(response => {
        this.proximates = response.data._embedded.proximates
        this.proximates.forEach(function (proximate, index) {
          _self.proximateTypes.push(proximate.proximateType)
        })
      })
    },
    refreshOtherNutrients () {
      let _self = this
      this.$http.get(this.product.nutrientsInformation._links.otherNutrients.href + '?projection=inlinedOtherNutrient').then(response => {
        this.otherNutrients = response.data._embedded.otherNutrients
        this.otherNutrients.forEach(function (otherNutrient, index) {
          _self.otherNutrientTypes.push(otherNutrient.otherNutrientType)
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
        this.productPackages = []
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
        this.errors.clear('otherNutrientsForm')
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
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      return JSON.stringify(data)
    },
    transformNutrientsRequest (data, headers) {
      data.nutrientsInformation = this.convertEntityToURI(data.nutrientsInformation)
      return JSON.stringify(data)
    },
    updateMineral (mineral) {
      let _self = this
      mineral.nutrientsInformation = this.product.nutrientsInformation
      this.$http.patch('minerals/' + mineral.id, mineral, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.success(this.$messages.successAction)
      })
    },
    updateVitamin (vitamin) {
      let _self = this
      vitamin.nutrientsInformation = this.product.nutrientsInformation
      this.$http.patch('vitamins/' + vitamin.id, vitamin, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.success(this.$messages.successAction)
      })
    },
    updateLipid (lipid) {
      let _self = this
      lipid.nutrientsInformation = this.product.nutrientsInformation
      this.$http.patch('lipids/' + lipid.id, lipid, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.success(this.$messages.successAction)
      })
    },
    updateProximate (proximate) {
      let _self = this
      proximate.nutrientsInformation = this.product.nutrientsInformation
      this.$http.patch('proximates/' + proximate.id, proximate, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
        this.success(this.$messages.successAction)
      })
    },
    updateOtherNutrient (otherNutrient) {
      let _self = this
      this.$http.patch('otherNutrients/' + otherNutrient.id, otherNutrient, {
        transformRequest: [function (data, headers) {
          return _self.transformNutrientsRequest(data, headers)
        }]
      }).then(response => {
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
    nutrientsInformation: null
  }
}
// TODO make ingredient dropdown keydown search (in case ingredients become more than 1000
