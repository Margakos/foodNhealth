<template>
  <div class="wrapper">
    <div class="animated fadeIn">
      <div class="row">
        <div class="col-lg-12">
          <b-card>
            <div slot="header">
              <i class='icon-docs'></i> Άτομα
            </div>
            <data-table :api-url="url"
                        :fields="fields"
                        :append-params="append_params" :per-page="rowsPerPage"></data-table>
            <div slot="footer">
              <b-button size="lg" variant="primary" class="pull-right" @click="addClientChoice"><i class="fa fa-plus"></i>
                Προσθήκη
              </b-button>
            </div>
          </b-card>
        </div>
      </div>
      <el-dialog title="Δημιουργία Ατόμου" :visible.sync="clientAddTypeVisible" :modal="true"
                 :close-on-click-modal="false"
                 :close-on-press-escape="false" :modal-append-to-body="false" :show-close="false">
        <b-alert show variant="info">
          <i class="fa fa-info"></i>
          Επιλέξτε μεταξύ δημιουργίας νέου Ατόμου ή Εύρεσης υπάρχοντος
        </b-alert>

        <div class="row align-items-center justify-content-center">
          <b-form-radio-group size="lg" :options="clientAddTypes" v-model="clientAddType"></b-form-radio-group>
        </div>

        <span slot="footer" class="dialog-footer">
          <b-button size="sm" variant="success" @click="addClient"><i class="fa fa-dot-circle-o"></i> Δημιουργία</b-button>
          <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
        </span>
      </el-dialog>
    </div>
    <client></client>
  </div>
</template>


<script src="./ClientsVM.js"></script>
