<template>
  <b-modal title="Διαχείριση Συσκευασίας" id="ProductPackageModal" v-model="visible" :no-close-on-backdrop="true"  :no-close-on-esc="true" size="lg" @hide="hide">
    <b-form :novalidate="false">
      <div class="row">
        <div class="col-md-12">
          <!-- Title -->
          <b-form-group description="Εισάγετε το Όνομα Συσκευασίας"
                        :feedback="errors.first('title', 'generalForm')"
                        :state="isValid('title', 'generalForm')">
            <b-input-group>
              <b-form-input data-vv-scope="generalForm" name="title" type="text" placeholder="Όνομα Συσκευασίας"
                            v-model="productPackage.title"
                            v-validate="rules.title"
                            :state="isValid('title', 'generalForm')"
                            :class="{'is-invalid': errors.has('title', 'generalForm')}">
              </b-form-input>
            </b-input-group>
          </b-form-group>

          <!-- Quantity -->
          <b-form-group description="Ποσότητα Συσκευασίας (grams)"
                        :feedback="errors.first('quantity', 'generalForm')"
                        :state="isValid('quantity', 'generalForm')">
            <b-form-input data-vv-scope="generalForm" name="quantity" type="number"
                          v-model="productPackage.quantity"
                          v-validate="rules.quantity"
                          :state="isValid('quantity', 'generalForm')"
                          :class="{'is-invalid': errors.has('quantity', 'generalForm')}">
            </b-form-input>
          </b-form-group>

          <!-- NumPieces -->
          <b-form-group description="Αριθμός Κομματιών"
                        :feedback="errors.first('numPieces', 'generalForm')"
                        :state="isValid('numPieces', 'generalForm')"
                        v-show="quantified">
            <b-form-input data-vv-scope="generalForm" type="number" name="numPieces" id="numPieces"
                          v-model="productPackage.numPieces"
                          v-validate="rules.numPieces"
                          :state="isValid('numPieces', 'generalForm')"
                          :class="{'is-invalid': errors.has('numPieces', 'generalForm')}"
                          :disabled="!quantified">
            </b-form-input>
          </b-form-group>

          <!-- Price -->
          <b-form-group description="Συμπληρώστε την Τιμή της Συσκευασίας"
                        :feedback="errors.first('price', 'generalForm')"
                        :state="isValid('price', 'generalForm')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-money'></i>
              </b-input-group-addon>
              <money data-vv-scope="generalForm" name="price" id="price" v-model="productPackage.price"
                     v-validate="rules.price"
                     :state="isValid('price', 'generalForm')"
                     :class="{'form-control' : true, 'is-invalid': errors.has('price', 'generalForm')}"/>
              <b-input-group-addon>
                <i class='fa fa-euro'></i>
              </b-input-group-addon>
            </b-input-group>
          </b-form-group>

          <!-- Supermarket -->
          <b-form-group description="Επιλέξτε Supermarket"
                        :feedback="errors.first('supermarket', 'generalForm')"
                        :state="isValid('supermarket', 'generalForm')">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           data-vv-scope="generalForm" name="supermarket" id="supermarket"
                           v-model="productPackage.supermarket" :options="supermarkets"
                           :searchable="true" placeholder="Supermarket"
                           track-by="id" label="title"
                           v-validate="rules.supermarket"
                           :state="isValid('supermarket', 'generalForm')"
                           :class="{'is-invalid': errors.has('supermarket', 'generalForm')}">
              </multiselect>
            </b-input-group>
          </b-form-group>
        </div>
      </div>
    </b-form>

    <div slot="modal-footer">
      <b-button size="sm" variant="success" @click="save" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
      <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i>Διαγραφή</b-button>
      <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>
  </b-modal>
</template>
<script src="./ProductPackageVM.js"></script>
