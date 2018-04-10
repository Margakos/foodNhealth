import DataTable from '@/foodNhealth/components/DataTable'
import Nutritionist from '@/foodNhealth/components/Nutritionist'

export default {
  components: {
    DataTable,
    Nutritionist
  },
  created () {
    console.log('Nutritionists created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onNutritionistSelected(eventData))
    this.$events.$on('nutritionist-edited', eventData => this.onNutritionistEdited(eventData))
    console.log('Nutritionists mounted')
  },
  beforeDestroy () {
    // un-subscribe from events
    this.$events.$off('row-selected')
    this.$events.$off('nutritionist-edited')
  },
  destroyed () {
    console.log('Nutritionists destroyed')
  },
  data: function () {
    return {
      url: 'nutritionists/search/findByQuery',
      fields: [
        {
          name: 'id',
          title: 'Α/Α'
        },
        {
          name: 'firstName',
          title: 'Όνομα'
        },
        {
          name: 'lastName',
          title: 'Επώνυμο'
        },
        'email'
      ],
      append_params: {
        query: ''
      }
    }
  },
  methods: {
    createNutritionist (event) {
      console.log('fire edit-nutritionist event')
      this.$events.fire('edit-nutritionist', null)
    },
    onNutritionistSelected (dataItem) {
      console.log('fire edit-nutritionist event')
      this.$events.fire('edit-nutritionist', dataItem)
    },
    onNutritionistEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
