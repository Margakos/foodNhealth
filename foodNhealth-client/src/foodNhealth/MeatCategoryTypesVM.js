import DataTable from '@/foodNhealth/components/DataTable'
import MeatCategoryType from '@/foodNhealth/components/MeatCategoryType'

export default {
  components: {
    DataTable,
    MeatCategoryType
  },
  created () {
    console.log('MeatCategoryType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onMeatCategoryTypeSelected(eventData))
    this.$events.$on('meatCategoryType-edited', eventData => this.onMeatCategoryTypeEdited(eventData))
    console.log('MeatCategoryType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('meatCategoryType-edited')
  },
  destroyed: function () {
    console.log('MeatCategoryType destroyed')
  },
  data: function () {
    return {
      url: 'meatCategoryTypes/search/findByQuery',
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
          name: 'foodCategorySubType.title',
          title: 'Τύπος Υποκατηγορίας'
        },
        {
          name: 'description',
          title: 'Περιγραφή'
        }
      ],
      append_params: {
        query: '',
        projection: 'inlinedMeatCategoryType'
      }
    }
  },
  methods: {
    createMeatCategoryType (event) {
      console.log('fire edit-meatCategoryType event')
      this.$events.fire('edit-meatCategoryType', null)
    },
    onMeatCategoryTypeSelected (dataItem) {
      console.log('fire edit-meatCategoryType event')
      this.$events.fire('edit-meatCategoryType', dataItem)
    },
    onMeatCategoryTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
