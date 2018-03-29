import DataTable from '@/foodNhealth/components/DataTable'
import RecipeCategory from '@/foodNhealth/components/RecipeCategory'

export default {
  components: {
    DataTable,
    RecipeCategory
  },
  created () {
    console.log('RecipeCategory created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onRecipeCategorySelected(eventData))
    this.$events.$on('recipeCategory-edited', eventData => this.onRecipeCategoryEdited(eventData))
    console.log('RecipeCategory mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('recipeCategory-edited')
  },
  destroyed: function () {
    console.log('RecipeCategory destroyed')
  },
  data: function () {
    return {
      url: 'recipeCategories/search/findByQuery',
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
    createRecipeCategory (event) {
      console.log('fire edit-recipeCategory event')
      this.$events.fire('edit-recipeCategory', null)
    },
    onRecipeCategorySelected (dataItem) {
      console.log('fire edit-recipeCategory event')
      this.$events.fire('edit-recipeCategory', dataItem)
    },
    onRecipeCategoryEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
