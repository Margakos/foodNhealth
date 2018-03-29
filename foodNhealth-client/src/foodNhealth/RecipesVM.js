import DataTable from '@/foodNhealth/components/DataTable'
import Recipe from '@/foodNhealth/components/Recipe'

export default {
  components: {
    DataTable,
    Recipe
  },
  created () {
    console.log('Recipe created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onRecipeSelected(eventData))
    this.$events.$on('recipe-edited', eventData => this.onRecipeEdited(eventData))
    console.log('Recipe mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('recipe-edited')
  },
  destroyed: function () {
    console.log('Recipe destroyed')
  },
  data: function () {
    return {
      url: 'recipes/search/findByQuery?projection=inlinedRecipe',
      fields: [
        {
          name: 'id',
          title: 'A/A'
        },
        {
          name: 'name',
          title: 'Όνομα'
        }
      ],
      append_params: {
        query: ''
      }
    }
  },
  methods: {
    createRecipe (event) {
      console.log('fire edit-recipe event')
      this.$events.fire('edit-recipe', null)
    },
    onRecipeSelected (dataItem) {
      console.log('fire edit-recipe event')
      this.$events.fire('edit-recipe', dataItem)
    },
    onRecipeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }

}
