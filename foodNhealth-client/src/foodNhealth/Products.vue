<template>
  <div class="wrapper">
    <div class="animated fadeIn">
      <div class="row">
        <div class="col-lg-12">
          <b-card>
            <div slot="header">
              <i class='icon-docs'></i> Προϊόντα
            </div>
            <data-table :api-url="url"
                        :fields="fields"
                        :append-params="append_params" :per-page="rowsPerPage"></data-table>
            <div slot="footer">
              <b-button size="lg" variant="primary" class="pull-right" @click="createProduct"><i class="fa fa-plus"></i>
                Δημιουργία
              </b-button>
            </div>
          </b-card>
        </div>
      </div>
    </div>
    <product></product>
  </div>
</template>


<script src="./ProductsVM.js"></script>
