import DataTable from '@/foodNhealth/components/DataTable'
import FoodCategoryCoreType from '@/foodNhealth/components/FoodCategoryCoreType'

export default {
  components: {
    DataTable,
    FoodCategoryCoreType
  },
  created () {
    console.log('FoodCategoryCoreType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onFoodCategoryCoreTypeSelected(eventData))
    this.$events.$on('foodCategoryCoreType-edited', eventData => this.onFoodCategoryCoreTypeEdited(eventData))
    console.log('FoodCategoryCoreType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('foodCategoryCoreType-edited')
  },
  destroyed: function () {
    console.log('FoodCategoryCoreType destroyed')
  },
  data: function () {
    return {
      url: 'foodCategoryCoreTypes/search/findByQuery',
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
    createFoodCategoryCoreType (event) {
      console.log('fire edit-foodCategoryCoreType event')
      this.$events.fire('edit-foodCategoryCoreType', null)
    },
    onFoodCategoryCoreTypeSelected (dataItem) {
      console.log('fire edit-foodCategoryCoreType event')
      this.$events.fire('edit-foodCategoryCoreType', dataItem)
    },
    onFoodCategoryCoreTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
