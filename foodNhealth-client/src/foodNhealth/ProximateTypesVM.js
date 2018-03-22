import DataTable from '@/foodNhealth/components/DataTable'
import ProximateType from '@/foodNhealth/components/ProximateType'

export default {
  components: {
    DataTable,
    ProximateType
  },
  created () {
    console.log('ProximateType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onProximateTypeSelected(eventData))
    this.$events.$on('proximateType-edited', eventData => this.onProximateTypeEdited(eventData))
    console.log('ProximateType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('proximateType-edited')
  },
  destroyed: function () {
    console.log('ProximateType destroyed')
  },
  data: function () {
    return {
      url: 'proximateTypes/search/findByQuery',
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
    createProximateType (event) {
      console.log('fire edit-proximateType event')
      this.$events.fire('edit-proximateType', null)
    },
    onProximateTypeSelected (dataItem) {
      console.log('fire edit-proximateType event')
      this.$events.fire('edit-proximateType', dataItem)
    },
    onProximateTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
