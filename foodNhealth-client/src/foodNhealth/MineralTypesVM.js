import DataTable from '@/foodNhealth/components/DataTable'
import MineralType from '@/foodNhealth/components/MineralType'

export default {
  components: {
    DataTable,
    MineralType
  },
  created () {
    console.log('MineralType created')
  },
  mounted () {
    // subscribe to the 'row-selected' event (wherever it may come from, should come from the child table component)
    this.$events.$on('row-selected', eventData => this.onMineralTypeSelected(eventData))
    this.$events.$on('mineralType-edited', eventData => this.onMineralTypeEdited(eventData))
    console.log('MineralType mounted')
  },
  beforeDestroy: function () {
    this.$events.$off('row-selected')
    this.$events.$off('mineralType-edited')
  },
  destroyed: function () {
    console.log('MineralType destroyed')
  },
  data: function () {
    return {
      url: 'mineralTypes/search/findByQuery',
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
    createMineralType (event) {
      console.log('fire edit-mineralType event')
      this.$events.fire('edit-mineralType', null)
    },
    onMineralTypeSelected (dataItem) {
      console.log('fire edit-mineralType event')
      this.$events.fire('edit-mineralType', dataItem)
    },
    onMineralTypeEdited (dataItem) {
      console.log('fire data-changed event')
      this.$events.fire('data-changed')
    }
  }
}
