<template>
  <b-modal title="Διαχείριση Συστατικού" id="ingredientModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
    <b-form :novalidate="false">

      <div class="row">
        <div class="col-lg-12">
          <!-- Title -->
          <b-form-group description="Εισάγετε το Όνομα Συστατικού"
                        :feedback="errors.first('name', 'generalForm')"
                        :state="isValid('name', 'generalForm')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-tag'></i>
              </b-input-group-addon>
              <b-form-input data-vv-scope="generalForm" name="name" type="text" placeholder="Τίτλος"
                            v-model="ingredient.name"
                            v-validate="rules.name"
                            :state="isValid('name', 'generalForm')"
                            :class="{'is-invalid': errors.has('name', 'generalForm')}">

              </b-form-input>
            </b-input-group>
          </b-form-group>
        </div>
      </div>

      <b-tabs pills>
        <b-tab title="Γενικά Στοιχεία" active>
          <!-- Available Form -->
          <b-form-group description="Επιλέξτε Μορφή Συστατικού"
                        :feedback="errors.first('availableForm', 'generalForm')"
                        :state="isValid('availableForm', 'generalForm')">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           data-vv-scope="generalForm" name="availableForm" id="availableForm"
                           v-model="ingredient.availableForm" :options="availableForms" :custom-label="getMessage"
                           :searchable="true" placeholder="Μορφή Συστατικού"
                           v-validate="rules.availableForm"
                           :state="isValid('availableForm', 'generalForm')"
                           :class="{'is-invalid': errors.has('availableForm', 'generalForm')}">
              </multiselect>
            </b-input-group>
          </b-form-group>

        </b-tab>

        <b-tab title="Θρεπτικά Συστατικά" :disabled="ingredient.id == null">
          <b-tabs pills vertical card>

            <b-tab title="Ιχνοστοιχεία" active>
              <div class="row">
                <div class="col-6">
                  <b-form-group description="Επιλέξτε Τύπο Ιχνοστοιχείου"
                                :feedback="errors.first('mineralType', 'mineralsForm')"
                                :state="isValid('mineralType', 'mineralsForm')">
                    <b-input-group>
                      <multiselect data-vv-scope="mineralsForm" name="mineralType" id="mineralType" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                                   v-model="mineralType" :options="mineralTypes"
                                   :searchable="true" placeholder="Τύπος Ιχνοστοιχείου"
                                   track-by="id" label="title"
                                   v-validate="rules.mineralType"
                                   :state="isValid('mineralType', 'mineralsForm')"
                                   :class="{'is-invalid': errors.has('mineralType', 'mineralsForm')}">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-4">
                  <b-form-group description="Ποσότητα Ιχνοστοιχείου (γραμμάρια)"
                                :feedback="errors.first('quantity', 'mineralsForm')"
                                :state="isValid('quantity', 'mineralsForm')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='icon-speedometer'></i>
                      </b-input-group-addon>
                      <b-form-input data-vv-scope="mineralsForm" type="number" name="quantity" id="quantity"
                                    v-model="mineralQuantity" v-validate="rules.quantity"
                                    :state="isValid('quantity', 'mineralsForm')"
                                    :class="{ 'is-invalid': errors.has('quantity', 'mineralsForm')}">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-2">
                  <b-button variant="primary" @click="addMineral" class="pull-right" :disabled="errors.any('mineralsForm')"><i class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </div>

              <b-table striped hover :items="minerals" :fields="mineralsFields">
                <!-- virtual index column -->
                <template slot="index" slot-scope="data">
                  {{data.index + 1}}
                </template>
                <template slot="mineralType" slot-scope="data">
                  {{data.value.title}}
                </template>
                <template slot="quantity" slot-scope="data">
                  {{data.value}}
                </template>
                <template slot="actions" slot-scope="row">
                  <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                  <b-button variant="danger" @click.stop="confirmRemoveMineral(row.item)"><i
                    class="fa fa-remove"></i></b-button>
                </template>
              </b-table>
            </b-tab>
          </b-tabs>


        </b-tab>
      </b-tabs>

    </b-form>

    <div slot="modal-footer">
      <b-button size="sm" variant="success" @click="save" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
      <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
      <b-button size="sm" variant="warning" @click="cancel" type="reset"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>
  </b-modal>
</template>

<script src="./IngredientVM.js"></script>
