export default {
  name: 'selectedRecipe',
  components: {},
  data: function () {
    return {
      visible: false,
      recipes: [],
      selectedRecipe: initSelectedRecipe(),
      selectedRecipeRows: [],
      ingredients: [],
      productPackages: [],
      selectedProductPackages: [],
      minerals: [],
      vitamins: [],
      lipids: [],
      proximates: [],
      otherNutrients: [],
      rules: {
        name: {
          required: true
        },
        recipe: {
          required: true
        },
        product: {
          required: true
        },
        productPackage: {
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
        }
      ]
    }
  },
  created () {
    Promise.all([this.getRecipes()]).then(([recipes]) => {
      this.recipes = recipes.data._embedded.recipes
    })
    console.log('SelectedRecipe created')
  },
  mounted () {
    this.$events.$on('edit-selectedRecipe', eventData => this.onEditSelectedRecipe(eventData))
    console.log('SelectedRecipe mounted')
  },
  destroyed: function () {
    this.$events.$off('edit-selectedRecipe')
    console.log('SelectedRecipe destroyed')
  },
  computed: {
    isDeletable () {
      return this.selectedRecipe.id != null
    }
  },
  methods: {
    getSelectedProductPackages () {
      let _self = this
      this.selectedRecipeRows.forEach(function (row, index) {
        _self.selectedProductPackages[index].productPackage = row.productPackage
        _self.selectedProductPackages[index].quantity = row.quantity
      })
    },
    refreshSelectedProductPackages () {
      let _self = this
      this.$http.get(this.selectedRecipe._links.selectedProductPackages.href + '?projection=inlinedSelectedProductPackage').then(response => {
        this.selectedProductPackages = response.data._embedded.selectedProductPackages
        // Need below in order for the table to create rows
        this.selectedProductPackages.forEach(function (selectedProductPackage, index) {
          _self.selectedRecipeRows.push([])
        })
      })
    },
    refreshProducts (recipe) {
      let _self = this
      this.$http.get('products/search/findProductsByRecipe?recipeId=' + recipe.id + '&projection=inlinedProduct').then(response => {
        _self.selectedRecipeRows = this.separateProducts(this.sortProducts(response.data._embedded.products))
        this.initSelectedProductPackages()
      })
    },
    refreshProductPackages (product, index) {
      this.$http.get(product._links.productPackages.href + '?projection=inlinedProductPackage').then(response => {
        this.selectedRecipeRows[index].productPackages = response.data._embedded.productPackages
      })
    },
    initSelectedProductPackages () {
      let _self = this
      this.selectedRecipeRows.forEach(function (row, index) {
        _self.selectedProductPackages.push(initSelectedProductPackage(_self.selectedRecipe))
      })
    },
    sortProducts (products) {
      products.sort(function (a, b) {
        // normalization helps if name has special characters as ά or ϊ etc
        a = a.ingredient.name.normalize('NFD').replace(/[\u0300-\u036f]/g, '')
        b = b.ingredient.name.normalize('NFD').replace(/[\u0300-\u036f]/g, '')
        return a < b
      })
      return products
    },
    separateProducts (products) {
      let _self = this
      let selectedRecipeRows = []
      let subProducts = []
      let ingredient = products[0].ingredient
      products.forEach(function (product, index) {
        if (products[index].ingredient.id !== ingredient.id) {
          let selectedRecipeRow = initSelectedRecipeRow()
          ingredient = products[index].ingredient
          selectedRecipeRow.products = _self.copyProducts(subProducts)
          selectedRecipeRows.push(selectedRecipeRow)
          subProducts = []
          subProducts.push(product)
        } else {
          subProducts.push(product)
        }
      })
      let selectedRecipeRow = initSelectedRecipeRow()
      selectedRecipeRow.products = subProducts
      selectedRecipeRows.push(selectedRecipeRow)
      return selectedRecipeRows
    },
    copyProducts (subProducts) {
      let products = []
      subProducts.forEach(function (product, index) {
        products.push(product)
      })
      return products
    },
    onEditSelectedRecipe (eventData) {
      this.invalidateAll()
      if (eventData != null) {
        // Edit existing row
        this.$http.get('selectedRecipes/' + eventData + '?projection=inlinedSelectedRecipe').then(response => {
          this.selectedRecipe = response.data
          this.refreshSelectedProductPackages()
          this.visible = true
        }).catch(e => {
          console.log(e)
          this.error(this.$messages.errorLoad)
        })
      } else {
        // Add new SelectedRecipe
        Object.assign(this.$data.selectedRecipe, initSelectedRecipe())
        this.visible = true
      }
    },
    beforeSave () {
      let _self = this
      this.$validator.validateAll('generalForm').then((result) => {
        if (!result) {
          return
        }
        // Custom Quantity validation
        let quantityValid = true
        this.selectedRecipeRows.forEach(function (row, index) {
        // eslint-disable-next-line
          if (row.quantity == 0) {
            _self.$validator.errors.add({field: 'quantity_' + index, msg: _self.$messages.fieldMoreThanZero, scope: 'generalForm'})
            quantityValid = false
          }
        })
        if (!quantityValid) {
          return
        }
        this.getSelectedProductPackages()
        this.save()
      })
    },
    save () {
      let _self = this
      let tempSelectedRecipe = Object.assign({}, this.selectedRecipe)
      if (this.selectedRecipe.id != null) {
        // existing selectedRecipe , update
        this.$http.patch('selectedRecipes/' + this.selectedRecipe.id + '?projection=inlinedSelectedRecipe', tempSelectedRecipe, {
          transformRequest: [function (data, headers) {
            return _self.transformRequest(data, headers)
          }]
        }).then(response => this.handleSuccess(response))
          .catch(e => this.handleError(e))
      } else {
        this.$http.post('selectedRecipes?projection=inlinedSelectedRecipe', tempSelectedRecipe, {
          transformRequest: [function (data, headers) {
            return _self.transformRequest(data, headers)
          }]
        }).then(response => {
          let tempSelectedProductPackages = Object.assign([{}], this.selectedProductPackages)
          this.$http.post('selectedRecipes/' + response.data.id + '/selectedProductPackages', tempSelectedProductPackages).then(resp => {
            this.handleSuccess(response)
          }).catch(e => this.handleError(e))
        })
          .catch(e => this.handleError(e))
      }
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
        this.$http.delete('selectedRecipes/' + this.selectedRecipe.id).then(response => {
          this.visible = false
          this.success(this.$messages.successAction)
          console.log('fire selectedRecipe-edited event')
          this.$events.fire('selectedRecipe-edited', this.selectedRecipe)
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
      })
      this.selectedProductPackages = []
      this.selectedRecipeRows = []
      this.ingredients = []
      this.productPackages = []
      this.minerals = []
      this.vitamins = []
      this.lipids = []
      this.proximates = []
      this.otherNutrients = []
    },
    handleSuccess (response) {
      this.selectedRecipe = response.data
      this.success(this.$messages.successAction)
      console.log('fire selectedRecipe-edited event')
      this.$events.fire('selectedRecipe-edited', this.selectedRecipe)
    },
    transformRequest (data, headers) {
      data.recipe = this.convertEntityToURI(data.recipe)
      return JSON.stringify(data)
    },
    recipeChanged (recipe, id) {
      if (recipe == null) {
        this.selectedRecipeRows = []
        this.selectedProductPackages = []
        return
      }
      this.selectedRecipe.name = recipe.name
      this.refreshProducts(recipe)
    },
    productChanged (selectedRecipeRow) {
      let index = this.selectedRecipeRows.indexOf(selectedRecipeRow)
      this.selectedRecipeRows[index].productPackage = null
      if (selectedRecipeRow.product == null) {
        this.selectedRecipeRows[index].productPackages = []
        return
      }
      this.refreshProductPackages(selectedRecipeRow.product, index)
    },
    customPackageLabel (productPackage) {
      return productPackage.title + ' - ' + productPackage.quantity + ' gr - ' + this.formatAmount(productPackage.price) + ' - ' + productPackage.supermarket.title
    }
  }
}

function initSelectedRecipe () {
  return {
    id: null,
    name: '',
    recipe: null
  }
}

function initSelectedRecipeRow () {
  return {
    products: [],
    productPackages: [],
    product: null,
    productPackage: null,
    quantity: 0
  }
}

function initSelectedProductPackage (selectedRecipe) {
  return {
    id: null,
    selectedRecipe: selectedRecipe,
    productPackage: null,
    quantity: 0
  }
}
