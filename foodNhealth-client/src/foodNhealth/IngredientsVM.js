import DataTable from '@/foodNhealth/components/DataTable'
import Ingredient from '@/foodNhealth/components/Ingredient'

export default {
  components: {
    DataTable,
    Ingredient
  },
  created () {
    console.log('Ingredient created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onIngredientSelected(eventData))
    this.$events.$on('ingredient-edited', eventData => this.onIngredientEdited(eventData))
    console.log('Ingredient mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('ingredient-edited')
  },
  destroyed: function () {
    console.log('Ingredient destroyed')
  },
  data: function () {
    return {
      url: 'ingredients/search/findByQuery?projection=inlinedIngredient',
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
          name: 'foodCategoryCoreType.title',
          title: 'Κύρια Κατηγορία Συστατικού',
          callback: 'getMessage'
        },
        {
          name: 'foodCategorySubType.title',
          title: 'Υποκατηγορία Συστατικού',
          callback: 'getMessage'
        }
      ],
      append_params: {
        query: ''
      }
    }
  },
  methods: {
    createIngredient (event) {
      console.log('fire edit-ingredient event')
      this.$events.fire('edit-ingredient', null)
    },
    onIngredientSelected (dataItem) {
      console.log('fire edit-ingredient event')
      this.$events.fire('edit-ingredient', dataItem)
    },
    onIngredientEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }

}
