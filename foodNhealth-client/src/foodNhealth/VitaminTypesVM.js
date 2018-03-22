import DataTable from '@/foodNhealth/components/DataTable'
import VitaminType from '@/foodNhealth/components/VitaminType'

export default {
  components: {
    DataTable,
    VitaminType
  },
  created () {
    console.log('VitaminType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onVitaminTypeSelected(eventData))
    this.$events.$on('vitaminType-edited', eventData => this.onVitaminTypeEdited(eventData))
    console.log('VitaminType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('vitaminType-edited')
  },
  destroyed: function () {
    console.log('VitaminType destroyed')
  },
  data: function () {
    return {
      url: 'vitaminTypes/search/findByQuery',
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
    createVitaminType (event) {
      console.log('fire edit-vitaminType event')
      this.$events.fire('edit-vitaminType', null)
    },
    onVitaminTypeSelected (dataItem) {
      console.log('fire edit-vitaminType event')
      this.$events.fire('edit-vitaminType', dataItem)
    },
    onVitaminTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
