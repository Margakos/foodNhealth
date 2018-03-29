import DataTable from '@/foodNhealth/components/DataTable'
import Supermarket from '@/foodNhealth/components/Supermarket'

export default {
  components: {
    DataTable,
    Supermarket
  },
  created () {
    console.log('Supermarket created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onSupermarketSelected(eventData))
    this.$events.$on('supermarket-edited', eventData => this.onSupermarketEdited(eventData))
    console.log('Supermarket mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('supermarket-edited')
  },
  destroyed: function () {
    console.log('Supermarket destroyed')
  },
  data: function () {
    return {
      url: 'supermarkets/search/findByQuery',
      fields: [
        {
          name: 'id',
          title: 'A/A'
        },
        {
          name: 'title',
          title: 'Όνομα'
        },
        {
          name: 'description',
          title: 'Περιγραφή'
        }
      ],
      append_params: {
        query: '',
        projection: 'inlinedSupermarket'
      }
    }
  },
  methods: {
    createSupermarket (event) {
      console.log('fire edit-supermarket event')
      this.$events.fire('edit-supermarket', null)
    },
    onSupermarketSelected (dataItem) {
      console.log('fire edit-supermarket event')
      this.$events.fire('edit-supermarket', dataItem)
    },
    onSupermarketEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
