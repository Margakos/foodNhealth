import DataTable from '@/foodNhealth/components/DataTable'
import OtherNutrientType from '@/foodNhealth/components/OtherNutrientType'

export default {
  components: {
    DataTable,
    OtherNutrientType
  },
  created () {
    console.log('OtherNutrientType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onOtherNutrientTypeSelected(eventData))
    this.$events.$on('otherNutrientType-edited', eventData => this.onOtherNutrientTypeEdited(eventData))
    console.log('OtherNutrientType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('otherNutrientType-edited')
  },
  destroyed: function () {
    console.log('OtherNutrientType destroyed')
  },
  data: function () {
    return {
      url: 'otherNutrientTypes/search/findByQuery',
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
    createOtherNutrientType (event) {
      console.log('fire edit-otherNutrientType event')
      this.$events.fire('edit-otherNutrientType', null)
    },
    onOtherNutrientTypeSelected (dataItem) {
      console.log('fire edit-otherNutrientType event')
      this.$events.fire('edit-otherNutrientType', dataItem)
    },
    onOtherNutrientTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
