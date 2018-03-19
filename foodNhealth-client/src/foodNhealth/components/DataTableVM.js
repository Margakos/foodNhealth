// Import the dist package (as per: http://bit.ly/2hkwl3e)
import { Vuetable, VuetablePagination, VuetablePaginationInfo } from 'vuetable-2'
// import directly from source. Ensure that following development (--save-dev) dependencies are installed (as per http://bit.ly/2ysn9lj)
// babel-plugin-transform-runtime
// babel-preset-stage-2
// babel-preset-es2015
// import Vuetable from 'vuetable-2/src/components/Vuetable'
// import VuetablePagination from 'vuetable-2/src/components/VuetablePagination'
// import VuetablePaginationInfo from 'vuetable-2/src/components/VuetablePaginationInfo'
import FilterBar from '@/foodNhealth/components/FilterBar'

export default {
  name: 'data-table',
  components: {
    Vuetable,
    VuetablePagination,
    VuetablePaginationInfo,
    FilterBar
  },
  props: {
    loadOnStart: {
      type: Boolean,
      required: false,
      default: true
    },
    apiUrl: {
      type: String,
      required: true
    },
    fields: {
      type: Array,
      required: true
    },
    sortOrder: {
      type: Array,
      default () {
        return [{field: 'id', direction: 'desc'}]
      }
    },
    appendParams: {
      type: Object,
      default () {
        return {}
      }
    },
    // TODO remove deprecated 'perPage' property
    perPage: {
      type: Number,
      required: false
    },
    includeFilterBar: {
      type: Boolean,
      required: false,
      default: true
    }
  },
  data: function () {
    return {
      loadingInstance: null,
      query_params: {
        sort: 'sort',
        page: 'page',
        perPage: 'size'
      },
      data_path: 'data',
      pagination_path: 'pagination',
      css: {
        table: {
          tableClass: 'table table-hover'
        },
        paginationInfo: {
          infoClass: ''
        },
        pagination: {
          infoClass: 'pull-left',
          wrapperClass: 'pagination pull-right',
          activeClass: 'btn-primary disabled',
          disabledClass: 'disabled',
          pageClass: 'page-link',
          linkClass: 'page-link',
          icons: {
            first: 'fa fa-angle-double-left',
            prev: 'fa fa-angle-left',
            next: 'fa fa-angle-right',
            last: 'fa fa-angle-double-right'
          }
        }
      },
      infoTemplate: 'Εμφανίζονται {from} εώς {to} από {total} εγγραφές',
      noDataTemplate: 'Δεν υπάρχουν εγγραφές'
    }
  },
  created () {
    console.log('DataTable created')
  },
  mounted () {
    this.$events.$on('filter-set', eventData => this.onFilterSet(eventData))
    this.$events.$on('filter-reset', e => this.onFilterReset())
    this.$events.$on('data-changed', e => this.refresh())
    console.log('DataTable mounted')
  },
  destroyed: function () {
    this.$events.$off('filter-set')
    this.$events.$off('filter-reset')
    this.$events.$off('data-changed')
    console.log('DataTable destroyed')
  },
  computed: {
    fetchDataEnabled () {
      console.log('fetch data enabled', this.apiUrl)
      return this.apiUrl !== ''
    }
  },
  watch: {
    apiUrl (newValue) {
      // clear any existing data when data URL is emptied
      if (newValue === '') {
        this.$refs.vuetable.resetData()
      }
    }
  },
  methods: {
    transform: function (data) {
      var transformed = {}
      transformed.data = typeof data === 'string' ? null : data._embedded[Object.keys(data._embedded)[0]]
      let stringData = typeof data === 'string'
      let lacksPagination = data.page == null
      transformed.links = {
        pagination: stringData ? {} : {
          total: lacksPagination ? transformed.data.length : data.page.totalElements,
          per_page: lacksPagination ? transformed.data.length : data.page.size,
          current_page: lacksPagination ? 1 : data.page.number + 1,
          last_page: lacksPagination ? 1 : data.page.totalPages,
          from: lacksPagination ? 1 : ((data.page.number) * data.page.size) + 1,
          to: lacksPagination ? 1 : (data.page.totalElements > (((data.page.number) * data.page.size) + data.page.size) ? (((data.page.number) * data.page.size) + data.page.size) : data.page.totalElements)
        }
      }
      return transformed
    },
    onPaginationData (paginationData) {
      this.$refs.pagination.setPaginationData(paginationData)
      this.$refs.paginationInfo.setPaginationData(paginationData)
    },
    onChangePage (page) {
      this.$refs.vuetable.changePage(page)
    },
    onFilterSet (filterText) {
      console.log('filter-set', filterText)
      this.appendParams.query = filterText
      this.$nextTick(() => this.$refs.vuetable.refresh())
    },
    onFilterReset () {
      this.appendParams.query = ''
      if (this.includeFilterBar) {
        // trigger refresh only when a 'built-in' filter bar is present, otherwise leave control to 'outside world'
        this.$nextTick(() => this.$refs.vuetable.refresh())
      }
    },
    onRowClicked (dataItem, event) {
      // trigger an event for any subscribed listeners
      this.$events.fire('row-selected', dataItem.id)
    },
    onActionPerformed (dataItem, eventToFire) {
      // trigger the passed event for any subscribed listeners
      this.$events.fire(eventToFire, dataItem.id)
    },
    refresh () {
      console.log('refresh')
      // TODO distinguish between insert/update/delete and refresh for delete, to avoid empty last page
      this.$nextTick(() => this.$refs.vuetable.reload())
    },
    onLoading () {
      this.loadingInstance = this.$loading()
    },
    onLoaded () {
      this.$nextTick(() => { this.loadingInstance = this.$loading().close() })
    },
    handleLoadError (response) {
      this.error(this.$messages.errorLoad)
    },
    changePageSize () {
      this.$nextTick(() => this.$refs.vuetable.refresh())
    },
    getSortParam: function (sortOrder) {
      return sortOrder.map(function (sort) {
        return sort.field + (sort.direction === 'desc' ? ',desc' : '')
      }).join(':')
    }
  }
}
