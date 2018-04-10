import DataTable from '@/foodNhealth/components/DataTable'
import Client from '@/foodNhealth/components/Client'

export default {
  components: {
    DataTable,
    Client
  },
  created () {
    console.log('Client created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onClientSelected(eventData))
    this.$events.$on('client-edited', eventData => this.onClientEdited(eventData))
    console.log('Client mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('client-edited')
  },
  destroyed: function () {
    console.log('Client destroyed')
  },
  data: function () {
    return {
      url: 'clients/search/findByQuery',
      fields: [
        {
          name: 'id',
          title: 'A/A'
        },
        {
          name: 'firstName',
          title: 'Όνομα'
        },
        {
          name: 'lastName',
          title: 'Επώνυμο'
        },
        {
          name: 'email',
          title: 'Email'
        }
      ],
      append_params: {
        query: '',
        email: this.$auth.user().email
      }
    }
  },
  methods: {
    addClient (event) {
      console.log('fire edit-client event')
      this.$events.fire('edit-client', null)
    },
    onClientSelected (dataItem) {
      console.log('fire edit-client event')
      this.$events.fire('edit-client', dataItem)
    },
    onClientEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
