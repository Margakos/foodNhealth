export default {
  data () {
    return {
      filterText: ''
    }
  },
  created () {
    console.log('FilterBar created')
  },
  destroyed () {
    console.log('FilterBar destroyed')
  },
  methods: {
    doFilter () {
      console.log('doFilter:', this.filterText)
      this.$events.fire('filter-set', this.filterText)
    },
    resetFilter () {
      this.filterText = ''
      console.log('resetFilter')
      this.$events.fire('filter-reset')
    }
  }
}
