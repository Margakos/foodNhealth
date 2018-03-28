<template>
  <b-modal title="Διαχείριση Υποκατηγορίας Κρεατικών" id="meatCategoryTypeModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true">
    <b-form :novalidate="false">

      <div class="row">
        <div class="col-12">
          <b-form-group description="Εισάγετε τον τίτλο"
                        :feedback="errors.first('title')"
                        :state="isValid('title')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-tag'></i>
              </b-input-group-addon>
              <b-form-input name="title" type="text" placeholder="Τίτλος"
                            v-model="meatCategoryType.title"
                            v-validate="rules.title"
                            :state="isValid('title')">
              </b-form-input>
            </b-input-group>
          </b-form-group>

          <!-- Food Category Sub Type -->
          <b-form-group description="Επιλέξτε την Κύρια Κατηγορία που ανήκει η Υποκατηγορία"
                        :feedback="errors.first('foodCategorySubType')"
                        :state="isValid('foodCategorySubType')">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           name="foodCategorySubType" id="foodCategorySubType"
                           v-model="meatCategoryType.foodCategorySubType" :options="foodCategorySubTypes"
                           :searchable="true" placeholder="Κύρια Κατηγορία Υποκατηγορίας"
                           track-by="id" label="title"
                           v-validate="rules.foodCategorySubType"
                           :state="isValid('foodCategorySubType', 'generalForm')"
                           :class="{'is-invalid': errors.has('foodCategorySubType', 'generalForm')}">
              </multiselect>
            </b-input-group>
          </b-form-group>

          <b-form-group description="Εισάγετε την περιγραφή"
                        :feedback="errors.first('description')"
                        :state="isValid('description')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-vcard-o'></i>
              </b-input-group-addon>
              <b-form-input name="description" type="text" placeholder="Περιγραφή"
                            v-model="meatCategoryType.description"
                            v-validate="rules.description"
                            :state="isValid('description')"></b-form-input>
            </b-input-group>
          </b-form-group>
        </div>
      </div>

    </b-form>

    <div slot="modal-footer">
      <b-button  size="sm" variant="success" @click="save" :disabled="errors.any()"><i
        class="fa fa-dot-circle-o"></i> Αποθήκευση
      </b-button>
      <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
      <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>

  </b-modal>
</template>

<script src="./MeatCategoryTypeVM.js"></script>
