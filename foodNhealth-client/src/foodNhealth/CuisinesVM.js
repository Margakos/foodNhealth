import DataTable from '@/foodNhealth/components/DataTable'
import Cuisine from '@/foodNhealth/components/Cuisine'

export default {
  components: {
    DataTable,
    Cuisine
  },
  created () {
    console.log('Cuisine created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onCuisineSelected(eventData))
    this.$events.$on('cuisine-edited', eventData => this.onCuisineEdited(eventData))
    console.log('Cuisine mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('cuisine-edited')
  },
  destroyed: function () {
    console.log('Cuisine destroyed')
  },
  data: function () {
    return {
      url: 'cuisines/search/findByQuery',
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
        query: ''
      }
    }
  },
  methods: {
    createCuisine (event) {
      console.log('fire edit-cuisine event')
      this.$events.fire('edit-cuisine', null)
    },
    onCuisineSelected (dataItem) {
      console.log('fire edit-cuisine event')
      this.$events.fire('edit-cuisine', dataItem)
    },
    onCuisineEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
