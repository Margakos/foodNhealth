import DataTable from '@/foodNhealth/components/DataTable'
import SelectedRecipe from '@/foodNhealth/components/SelectedRecipe'

export default {
  components: {
    DataTable,
    SelectedRecipe
  },
  created () {
    console.log('SelectedRecipe created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onSelectedRecipeSelected(eventData))
    this.$events.$on('selectedRecipe-edited', eventData => this.onSelectedRecipeEdited(eventData))
    console.log('SelectedRecipe mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('selectedRecipe-edited')
  },
  destroyed: function () {
    console.log('SelectedRecipe destroyed')
  },
  data: function () {
    return {
      url: 'selectedRecipes/search/findByQuery',
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
          name: 'client.firstName' + ' ' + 'client.lastName',
          title: 'Ενδιαφερόμενος'
        },
        {
          name: 'name',
          title: 'Όνομα'
        },
        {
          name: 'client.email',
          title: 'Email'
        },
        {
          name: 'client.phoneNumber',
          title: 'Τηλέφωνο'
        }
      ],
      append_params: {
        query: '',
        projection: 'inlinedSelectedRecipe'
      }
    }
  },
  methods: {
    createSelectedRecipe (event) {
      console.log('fire edit-selectedRecipe event')
      this.$events.fire('edit-selectedRecipe', null)
    },
    onSelectedRecipeSelected (dataItem) {
      console.log('fire edit-selectedRecipe event')
      this.$events.fire('edit-selectedRecipe', dataItem)
    },
    onSelectedRecipeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }

}
