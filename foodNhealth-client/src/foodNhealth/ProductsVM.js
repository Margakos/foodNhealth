import DataTable from '@/foodNhealth/components/DataTable'
import Product from '@/foodNhealth/components/Product'

export default {
  components: {
    DataTable,
    Product
  },
  created () {
    console.log('Product created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onProductSelected(eventData))
    this.$events.$on('product-edited', eventData => this.onProductEdited(eventData))
    console.log('Product mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('product-edited')
  },
  destroyed: function () {
    console.log('Product destroyed')
  },
  data: function () {
    return {
      url: 'products/search/findByQuery?projection=inlinedProduct',
      fields: [
        {
          name: 'id',
          title: 'A/A'
        },
        {
          name: 'name',
          title: 'Όνομα'
        },
        {
          name: 'ingredient.name',
          title: 'Συστατικό'
        }
      ],
      append_params: {
        query: ''
      }
    }
  },
  methods: {
    createProduct (event) {
      console.log('fire edit-product event')
      this.$events.fire('edit-product', null)
    },
    onProductSelected (dataItem) {
      console.log('fire edit-product event')
      this.$events.fire('edit-product', dataItem)
    },
    onProductEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }

}
