<template>
  <div class="filter-bar pull-left">
    <div class="form-inline mr-2">
        <slot name="left-filter"></slot>
        <b-input-group>
          <b-form-input type="text" v-model="filterText" class="form-control" @keyup.enter.native="doFilter"
                        placeholder="Φίλτρο..."></b-form-input>
          <b-input-group-button slot="right">
            <b-button variant="primary" @click="doFilter"><i class="fa fa-search"></i></b-button>
          </b-input-group-button>
          <b-input-group-button slot="right">
            <b-button variant="danger" @click="resetFilter"><i class="fa fa-close"></i></b-button>
          </b-input-group-button>
        </b-input-group>

    </div>
  </div>
</template>
<script src="./FilterBarVM.js"></script>
