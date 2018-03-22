import DataTable from '@/foodNhealth/components/DataTable'
import LipidType from '@/foodNhealth/components/LipidType'

export default {
  components: {
    DataTable,
    LipidType
  },
  created () {
    console.log('LipidType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onLipidTypeSelected(eventData))
    this.$events.$on('lipidType-edited', eventData => this.onLipidTypeEdited(eventData))
    console.log('LipidType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('lipidType-edited')
  },
  destroyed: function () {
    console.log('LipidType destroyed')
  },
  data: function () {
    return {
      url: 'lipidTypes/search/findByQuery',
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
    createLipidType (event) {
      console.log('fire edit-lipidType event')
      this.$events.fire('edit-lipidType', null)
    },
    onLipidTypeSelected (dataItem) {
      console.log('fire edit-lipidType event')
      this.$events.fire('edit-lipidType', dataItem)
    },
    onLipidTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
