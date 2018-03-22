import DataTable from '@/foodNhealth/components/DataTable'
import FoodCategoryMainType from '@/foodNhealth/components/FoodCategoryMainType'

export default {
  components: {
    DataTable,
    FoodCategoryMainType
  },
  created () {
    console.log('FoodCategoryMainType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onFoodCategoryMainTypeSelected(eventData))
    this.$events.$on('foodCategoryMainType-edited', eventData => this.onFoodCategoryMainTypeEdited(eventData))
    console.log('FoodCategoryMainType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('foodCategoryMainType-edited')
  },
  destroyed: function () {
    console.log('FoodCategoryMainType destroyed')
  },
  data: function () {
    return {
      url: 'foodCategoryMainTypes/search/findByQuery',
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
    createFoodCategoryMainType (event) {
      console.log('fire edit-foodCategoryMainType event')
      this.$events.fire('edit-foodCategoryMainType', null)
    },
    onFoodCategoryMainTypeSelected (dataItem) {
      console.log('fire edit-foodCategoryMainType event')
      this.$events.fire('edit-foodCategoryMainType', dataItem)
    },
    onFoodCategoryMainTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
