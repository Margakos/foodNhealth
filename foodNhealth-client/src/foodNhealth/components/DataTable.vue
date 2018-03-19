<template>
  <div id="dataTableWrapper">
    <FilterBar>
      <div slot="left-filter">
        <slot name="left-side-search"></slot>
      </div>
    </FilterBar>
    <!-- page size selection -->
    <div class="filter-bar pull-left">
      <div class="form-inline mr-2">
        <b-input-group left="Εγγραφές">
          <b-form-select
            :plain="false"
            v-model="rowsPerPage"
            :options="rowsPerPageOptions" @input="changePageSize">
          </b-form-select>
        </b-input-group>
      </div>
    </div>
    <slot name="criteria"></slot>
    <vuetable ref="vuetable"
              :load-on-start="loadOnStart"
              :api-url="apiUrl"
              :fields="fields"
              :query-params="query_params"
              :append-params="appendParams"
              :sort-order="sortOrder"
              :per-page="rowsPerPage" :css="css.table"
              @vuetable:pagination-data="onPaginationData" @vuetable:row-clicked="onRowClicked"
              @vuetable:loading="onLoading" @vuetable:loaded="onLoaded"
              @vuetable:load-error="handleLoadError"
              :no-data-template="noDataTemplate" :http-fetch="customFetch" :reactive-api-url="fetchDataEnabled"></vuetable>
    <nav>
      <vuetable-pagination-info ref="paginationInfo" :css="css.pagination" :info-template="infoTemplate"
                                :no-data-template="noDataTemplate"></vuetable-pagination-info>
      <vuetable-pagination ref="pagination" @vuetable-pagination:change-page="onChangePage"
                           :css="css.pagination"></vuetable-pagination>
    </nav>
  </div>
</template>
<script src="./DataTableVM.js"></script>
