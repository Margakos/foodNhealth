import DataTable from '@/foodNhealth/components/DataTable'
import Allergy from '@/foodNhealth/components/Allergy'

export default {
  components: {
    DataTable,
    Allergy
  },
  created () {
    console.log('Allergy created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onAllergySelected(eventData))
    this.$events.$on('allergy-edited', eventData => this.onAllergyEdited(eventData))
    console.log('Allergy mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('allergy-edited')
  },
  destroyed: function () {
    console.log('Allergy destroyed')
  },
  data: function () {
    return {
      url: 'allergies/search/findByQuery',
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
    createAllergy (event) {
      console.log('fire edit-allergy event')
      this.$events.fire('edit-allergy', null)
    },
    onAllergySelected (dataItem) {
      console.log('fire edit-allergy event')
      this.$events.fire('edit-allergy', dataItem)
    },
    onAllergyEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
