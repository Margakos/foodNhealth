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
          <!-- Food Category Core Type -->
          <b-form-group description="Επιλέξτε την Κύρια Κατηγορία που ανήκει το Συστατικό"
                        :feedback="errors.first('foodCategoryCoreType', 'generalForm')"
                        :state="isValid('foodCategoryCoreType', 'generalForm')">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           data-vv-scope="generalForm" name="foodCategoryCoreType" id="foodCategoryCoreType"
                           v-model="ingredient.foodCategoryCoreType" :options="foodCategoryCoreTypes"
                           :searchable="true" placeholder="Κύρια Κατηγορία Συστατικού"
                           track-by="id" label="title"
                           v-validate="rules.foodCategoryCoreType"
                           :state="isValid('foodCategoryCoreType', 'generalForm')"
                           :class="{'is-invalid': errors.has('foodCategoryCoreType', 'generalForm')}"
                           @input="foodCategoryCoreTypeChanged">
              </multiselect>
            </b-input-group>
          </b-form-group>

          <!-- Food Category Sub Type -->
          <b-form-group description="Επιλέξτε την Υποκατηγορία που ανήκει το Συστατικό"
                        :feedback="errors.first('foodCategorySubType', 'generalForm')"
                        :state="isValid('foodCategorySubType', 'generalForm')">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           data-vv-scope="generalForm" name="foodCategorySubType" id="foodCategorySubType"
                           v-model="ingredient.foodCategorySubType" :options="foodCategorySubTypes"
                           :searchable="true" placeholder="Υποκατηγορία Συστατικού"
                           track-by="id" label="title"
                           v-validate="rules.foodCategorySubType"
                           :state="isValid('foodCategorySubType', 'generalForm')"
                           :class="{'is-invalid': errors.has('foodCategorySubType', 'generalForm')}"
                           :disabled="isFoodCategorySubTypeDisabled">
              </multiselect>
            </b-input-group>
          </b-form-group>

          <!--Quantified-->
          <b-form-group description="Είναι το Συστατικό σε τεμάχια">
            <b-form-checkbox data-vv-scope="generalForm" name="quantified"
                             v-model="ingredient.quantified" v-validate="rules.quantified"
                             :state="isValid('quantified', 'generalForm')"></b-form-checkbox>
            Τεμάχια: <strong>{{ ingredient.quantified ? getMessage('yes') : getMessage('no') }}</strong>
          </b-form-group>

          <div class="form action text-right">
            <b-button size="sm" variant="success" @click="save" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
            <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
          </div>
        </b-tab>

        <b-tab title="Θρεπτικά Συστατικά" :disabled="ingredient.id == null">
          <b-tabs pills vertical card>

            <b-tab title="Θρεπτικά Συστατικά" active>
              <div class="row">

                <b-table striped hover :items="proximates" :fields="proximatesFields">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="proximateType" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    <div class="col-8 center">
                      <b-form-group description="Ποσότητα Θρεπτικού Συστατικού (grams)"
                                    :feedback="errors.first('quantity_' + proximates[data.index].id, 'proximatesForm_' + data.index)"
                                    :state="isValid('quantity_' + proximates[data.index].id, 'proximatesForm_' + data.index)">
                        <b-form-input :data-vv-scope="'proximatesForm_' + data.index" type="number" :name="'quantity_' + proximates[data.index].id" :id="'quantity_' + proximates[data.index].id"
                                      v-model="proximates[data.index].quantity" :value="data.value"
                                      v-validate="rules.quantity"
                                      :state="isValid('quantity_' + proximates[data.index].id, 'proximatesForm_' + data.index)"
                                      :class="{'is-invalid': errors.has('quantity_' + proximates[data.index].id, 'proximatesForm_' + data.index)}">
                        </b-form-input>
                      </b-form-group>
                    </div>
                  </template>
                  <template slot="actions" slot-scope="row">
                    <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button variant="success" @click.stop="updateProximate(row.item)" :disabled="errors.any('proximatesForm_' + row.index) || ingredient.id == null"><i
                      class="fa fa-check"></i></b-button>
                  </template>
                </b-table>
              </div>

            </b-tab>

            <b-tab title="Ιχνοστοιχεία">
              <div class="row">
                <b-table striped hover :items="minerals" :fields="mineralsFields">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="mineralType" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    <div class="col-8 center">
                      <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                    :feedback="errors.first('quantity_' + minerals[data.index].id, 'mineralsForm_' + data.index)"
                                    :state="isValid('quantity_' + minerals[data.index].id, 'mineralsForm_' + data.index)">
                        <b-form-input :data-vv-scope="'mineralsForm_' + data.index" type="number" :name="'quantity_' + minerals[data.index].id" :id="'quantity_' + minerals[data.index].id"
                                      v-model="minerals[data.index].quantity" :value="data.value"
                                      v-validate="rules.quantity"
                                      :state="isValid('quantity_' + minerals[data.index].id, 'mineralsForm_' + data.index)"
                                      :class="{'is-invalid': errors.has('quantity_' + minerals[data.index].id, 'mineralsForm_' + data.index)}">
                        </b-form-input>
                      </b-form-group>
                    </div>
                  </template>
                  <template slot="actions" slot-scope="row">
                    <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button variant="success" @click.stop="updateMineral(row.item)" :disabled="errors.any('mineralsForm_' + row.index) || ingredient.id == null"><i
                      class="fa fa-check"></i></b-button>
                  </template>
                </b-table>
              </div>
            </b-tab>

            <b-tab title="Βιταμίνες">
              <div class="row">
                <b-table striped hover :items="vitamins" :fields="vitaminsFields">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="vitaminType" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    <div class="col-8 center">
                      <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                    :feedback="errors.first('quantity_' + vitamins[data.index].id, 'vitaminsForm_' + data.index)"
                                    :state="isValid('quantity_' + vitamins[data.index].id, 'vitaminsForm_' + data.index)">
                        <b-form-input :data-vv-scope="'vitaminsForm_' + data.index" type="number" :name="'quantity_' + vitamins[data.index].id" :id="'quantity_' + vitamins[data.index].id"
                                      v-model="vitamins[data.index].quantity" :value="data.value"
                                      v-validate="rules.quantity"
                                      :state="isValid('quantity_' + vitamins[data.index].id, 'vitaminsForm_' + data.index)"
                                      :class="{'is-invalid': errors.has('quantity_' + vitamins[data.index].id, 'vitaminsForm_' + data.index)}">
                        </b-form-input>
                      </b-form-group>
                    </div>
                  </template>
                  <template slot="actions" slot-scope="row">
                    <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button variant="success" @click.stop="updateVitamin(row.item)" :disabled="errors.any('vitaminsForm_' + row.index) || ingredient.id == null"><i
                      class="fa fa-check"></i></b-button>
                  </template>
                </b-table>
              </div>
            </b-tab>

            <b-tab title="Λιπίδια">
              <div class="row">
                <b-table striped hover :items="lipids" :fields="lipidsFields">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="lipidType" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    <div class="col-8 center">
                      <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                    :feedback="errors.first('quantity_' + lipids[data.index].id, 'lipidsForm_' + data.index)"
                                    :state="isValid('quantity_' + lipids[data.index].id, 'lipidsForm_' + data.index)">
                        <b-form-input :data-vv-scope="'lipidsForm_' + data.index" type="number" :name="'quantity_' + lipids[data.index].id" :id="'quantity_' + lipids[data.index].id"
                                      v-model="lipids[data.index].quantity" :value="data.value"
                                      v-validate="rules.quantity"
                                      :state="isValid('quantity_' + lipids[data.index].id, 'lipidsForm_' + data.index)"
                                      :class="{'is-invalid': errors.has('quantity_' + lipids[data.index].id, 'lipidsForm_' + data.index)}">
                        </b-form-input>
                      </b-form-group>
                    </div>
                  </template>
                  <template slot="actions" slot-scope="row">
                    <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button variant="success" @click.stop="updateLipid(row.item)" :disabled="errors.any('lipidsForm_' + row.index) || ingredient.id == null"><i
                      class="fa fa-check"></i></b-button>
                  </template>
                </b-table>
              </div>
            </b-tab>

            <b-tab title="Λοιπά">
              <div class="row">
                <b-table striped hover :items="otherNutrients" :fields="otherNutrientsFields">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="otherNutrientType" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    <div class="col-8 center">
                      <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                    :feedback="errors.first('quantity_' + otherNutrients[data.index].id, 'otherNutrientsForm_' + data.index)"
                                    :state="isValid('quantity_' + otherNutrients[data.index].id, 'otherNutrientsForm_' + data.index)">
                        <b-form-input :data-vv-scope="'otherNutrientsForm_' + data.index" type="number" :name="'quantity_' + otherNutrients[data.index].id" :id="'quantity_' + otherNutrients[data.index].id"
                                      v-model="otherNutrients[data.index].quantity" :value="data.value"
                                      v-validate="rules.quantity"
                                      :state="isValid('quantity_' + otherNutrients[data.index].id, 'otherNutrientsForm_' + data.index)"
                                      :class="{'is-invalid': errors.has('quantity_' + otherNutrients[data.index].id, 'otherNutrientsForm_' + data.index)}">
                        </b-form-input>
                      </b-form-group>
                    </div>
                  </template>
                  <template slot="actions" slot-scope="row">
                    <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button variant="success" @click.stop="updateOtherNutrient(row.item)" :disabled="errors.any('otherNutrientsForm_' + row.index) || ingredient.id == null"><i
                      class="fa fa-check"></i></b-button>
                  </template>
                </b-table>
              </div>
            </b-tab>

          </b-tabs>


        </b-tab>
      </b-tabs>

    </b-form>

    <div slot="modal-footer">
      <b-button size="sm" variant="warning" @click="cancel" type="reset"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>
  </b-modal>
</template>

<script src="./IngredientVM.js"></script>
