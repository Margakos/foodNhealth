<template>
  <div class="wrapper">
    <div class="animated fadeIn">
      <div class="row">
        <div class="col-lg-12">
          <b-card>
            <div slot="header">
              <i class='icon-people'></i> Χρήστες
            </div>
            <data-table :api-url="url"
                        :fields="fields"
                        :append-params="append_params"
                        :per-page="rowsPerPage"></data-table>
            <div slot="footer">
              <b-button size="lg" variant="primary" class="pull-right" @click="createPerson"><i class="fa fa-plus"></i>
                Δημιουργία
              </b-button>
            </div>
          </b-card>
        </div>
      </div>
    </div>
    <person></person>
  </div>
</template>
<script src="./PersonsVM.js"></script>
