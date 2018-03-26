import DataTable from '@/foodNhealth/components/DataTable'
import FoodCategorySubType from '@/foodNhealth/components/FoodCategorySubType'

export default {
  components: {
    DataTable,
    FoodCategorySubType
  },
  created () {
    console.log('FoodCategorySubType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onFoodCategorySubTypeSelected(eventData))
    this.$events.$on('foodCategorySubType-edited', eventData => this.onFoodCategorySubTypeEdited(eventData))
    console.log('FoodCategorySubType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('foodCategorySubType-edited')
  },
  destroyed: function () {
    console.log('FoodCategorySubType destroyed')
  },
  data: function () {
    return {
      url: 'foodCategorySubTypes/search/findByQuery',
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
          name: 'foodCategoryCoreType.title',
          title: 'Κύρια Κατηγορία Συστατικών'
        },
        {
          name: 'description',
          title: 'Περιγραφή'
        }
      ],
      append_params: {
        query: '',
        projection: 'inlinedFoodCategorySubType'
      }
    }
  },
  methods: {
    createFoodCategorySubType (event) {
      console.log('fire edit-foodCategorySubType event')
      this.$events.fire('edit-foodCategorySubType', null)
    },
    onFoodCategorySubTypeSelected (dataItem) {
      console.log('fire edit-foodCategorySubType event')
      this.$events.fire('edit-foodCategorySubType', dataItem)
    },
    onFoodCategorySubTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
