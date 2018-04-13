import DataTable from '@/foodNhealth/components/DataTable'
import AdminClient from '@/foodNhealth/components/AdminClient'

export default {
  components: {
    DataTable,
    AdminClient
  },
  created () {
    console.log('Clients created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onClientSelected(eventData))
    this.$events.$on('adminClient-edited', eventData => this.onClientEdited(eventData))
    console.log('Clients mounted')
  },
  beforeDestroy () {
    // un-subscribe from events
    this.$events.$off('row-selected')
    this.$events.$off('adminClient-edited')
  },
  destroyed () {
    console.log('Clients destroyed')
  },
  data: function () {
    return {
      url: 'clients/search/searchByQuery',
      fields: [
        {
          name: 'id',
          title: 'Α/Α'
        },
        {
          name: 'firstName',
          title: 'Όνομα'
        },
        {
          name: 'lastName',
          title: 'Επώνυμο'
        },
        'email'
      ],
      append_params: {
        query: ''
      }
    }
  },
  methods: {
    createClient (event) {
      console.log('fire edit-adminClient event')
      this.$events.fire('edit-adminClient', null)
    },
    onClientSelected (dataItem) {
      console.log('fire edit-adminClient event')
      this.$events.fire('edit-adminClient', dataItem)
    },
    onClientEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
