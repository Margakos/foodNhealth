import DataTable from '@/foodNhealth/components/DataTable'
import Person from '@/foodNhealth/components/Person'

export default {
  components: {
    DataTable,
    Person
  },
  created () {
    console.log('Persons created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onPersonSelected(eventData))
    this.$events.$on('person-edited', eventData => this.onPersonEdited(eventData))
    console.log('Persons mounted')
  },
  beforeDestroy () {
    // un-subscribe from events
    this.$events.$off('row-selected')
    this.$events.$off('person-edited')
  },
  destroyed () {
    console.log('Persons destroyed')
  },
  data: function () {
    return {
      url: 'persons/search/findByQuery',
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
    createPerson (event) {
      console.log('fire edit-person event')
      this.$events.fire('edit-person', null)
    },
    onPersonSelected (dataItem) {
      console.log('fire edit-person event')
      this.$events.fire('edit-person', dataItem)
    },
    onPersonEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
