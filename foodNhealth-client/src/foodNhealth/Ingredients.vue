<template>
  <div class="wrapper">
    <div class="animated fadeIn">
      <div class="row">
        <div class="col-lg-12">
          <b-card>
            <div slot="header">
              <i class='icon-docs'></i> Συστατικά
            </div>
            <data-table :api-url="url"
                        :fields="fields"
                        :append-params="append_params" :per-page="rowsPerPage"></data-table>
            <div slot="footer">
              <b-button size="lg" variant="primary" class="pull-right" @click="createIngredient"><i class="fa fa-plus"></i>
                Δημιουργία
              </b-button>
            </div>
          </b-card>
        </div>
      </div>
    </div>
    <ingredient></ingredient>
  </div>
</template>


<script src="./IngredientsVM.js"></script>
