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
                           @change="foodCategoryCoreTypeChanged">
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

          <!-- Meat Category Type -->
          <b-form-group description="Επιλέξτε την Υποκατηγορία Κρέατος που ανήκει το Συστατικό"
                        :feedback="errors.first('meatCategoryType', 'generalForm')"
                        :state="isValid('meatCategoryType', 'generalForm')"
                        v-show="this.ingredient.foodCategorySubType != null && this.ingredient.foodCategorySubType.title === 'Κρέας'">
            <b-input-group>
              <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                           data-vv-scope="generalForm" name="meatCategoryType" id="meatCategoryType"
                           v-model="ingredient.meatCategoryType" :options="meatCategoryTypes"
                           :searchable="true" placeholder="Υποκατηγορία Κρέατος"
                           track-by="id" label="title"
                           v-validate="rules.meatCategoryType"
                           :state="isValid('meatCategoryType', 'generalForm')"
                           :class="{'is-invalid': errors.has('meatCategoryType', 'generalForm')}"
                           :disabled="isMeatCategoryTypeDisabled">
              </multiselect>
            </b-input-group>
          </b-form-group>

        </b-tab>

        <b-tab title="Θρεπτικά Συστατικά" :disabled="ingredient.id == null">
          <b-tabs pills vertical card>

            <b-tab title="Θρεπτικά Συστατικά" active>
              <div class="row">
                <div class="col-6">
                  <b-form-group description="Επιλέξτε Τύπο Θρεπτικού Συστατικού"
                                :feedback="errors.first('proximateType', 'proximatesForm')"
                                :state="isValid('proximateType', 'proximatesForm')">
                    <b-input-group>
                      <multiselect data-vv-scope="proximatesForm" name="proximateType" id="proximateType" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                                   v-model="proximateType" :options="proximateTypes"
                                   :searchable="true" placeholder="Τύπος Θρεπτικού Συστατικού"
                                   track-by="id" label="title"
                                   v-validate="rules.proximateType"
                                   :state="isValid('proximateType', 'proximatesForm')"
                                   :class="{'is-invalid': errors.has('proximateType', 'proximatesForm')}">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-4">
                  <b-form-group description="Ποσότητα Ιχνοστοιχείου (γραμμάρια)"
                                :feedback="errors.first('quantity', 'proximatesForm')"
                                :state="isValid('quantity', 'proximatesForm')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='icon-speedometer'></i>
                      </b-input-group-addon>
                      <b-form-input data-vv-scope="proximatesForm" type="number" name="quantity" id="quantity"
                                    v-model="proximateQuantity" v-validate="rules.quantity"
                                    :state="isValid('quantity', 'proximatesForm')"
                                    :class="{ 'is-invalid': errors.has('quantity', 'proximatesForm')}">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-2">
                  <b-button variant="primary" @click="addProximate" class="pull-right" :disabled="errors.any('proximatesForm')"><i class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </div>

              <b-table striped hover :items="proximates" :fields="proximatesFields">
                <!-- virtual index column -->
                <template slot="index" slot-scope="data">
                  {{data.index + 1}}
                </template>
                <template slot="proximateType" slot-scope="data">
                  {{data.value.title}}
                </template>
                <template slot="quantity" slot-scope="data">
                  {{data.value}}
                </template>
                <template slot="actions" slot-scope="row">
                  <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                  <b-button variant="danger" @click.stop="confirmRemoveProximate(row.item)"><i
                    class="fa fa-remove"></i></b-button>
                </template>
              </b-table>
            </b-tab>

            <b-tab title="Ιχνοστοιχεία">
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

            <b-tab title="Βιταμίνες">
              <div class="row">
                <div class="col-6">
                  <b-form-group description="Επιλέξτε Τύπο Βιταμίνης"
                                :feedback="errors.first('vitaminType', 'vitaminsForm')"
                                :state="isValid('vitaminType', 'vitaminsForm')">
                    <b-input-group>
                      <multiselect data-vv-scope="vitaminsForm" name="vitaminType" id="vitaminType" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                                   v-model="vitaminType" :options="vitaminTypes"
                                   :searchable="true" placeholder="Τύπος Βιταμίνης"
                                   track-by="id" label="title"
                                   v-validate="rules.vitaminType"
                                   :state="isValid('vitaminType', 'vitaminsForm')"
                                   :class="{'is-invalid': errors.has('vitaminType', 'vitaminsForm')}">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-4">
                  <b-form-group description="Ποσότητα Ιχνοστοιχείου (γραμμάρια)"
                                :feedback="errors.first('quantity', 'vitaminsForm')"
                                :state="isValid('quantity', 'vitaminsForm')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='icon-speedometer'></i>
                      </b-input-group-addon>
                      <b-form-input data-vv-scope="vitaminsForm" type="number" name="quantity" id="quantity"
                                    v-model="vitaminQuantity" v-validate="rules.quantity"
                                    :state="isValid('quantity', 'vitaminsForm')"
                                    :class="{ 'is-invalid': errors.has('quantity', 'vitaminsForm')}">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-2">
                  <b-button variant="primary" @click="addVitamin" class="pull-right" :disabled="errors.any('vitaminsForm')"><i class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </div>

              <b-table striped hover :items="vitamins" :fields="vitaminsFields">
                <!-- virtual index column -->
                <template slot="index" slot-scope="data">
                  {{data.index + 1}}
                </template>
                <template slot="vitaminType" slot-scope="data">
                  {{data.value.title}}
                </template>
                <template slot="quantity" slot-scope="data">
                  {{data.value + ' gr'}}
                </template>
                <template slot="actions" slot-scope="row">
                  <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                  <b-button variant="danger" @click.stop="confirmRemoveVitamin(row.item)"><i
                    class="fa fa-remove"></i></b-button>
                </template>
              </b-table>
            </b-tab>

            <b-tab title="Λιπίδια">
              <div class="row">
                <div class="col-6">
                  <b-form-group description="Επιλέξτε Τύπο Λιπιδίου"
                                :feedback="errors.first('lipidType', 'lipidsForm')"
                                :state="isValid('lipidType', 'lipidsForm')">
                    <b-input-group>
                      <multiselect data-vv-scope="lipidsForm" name="lipidType" id="lipidType" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                                   v-model="lipidType" :options="lipidTypes"
                                   :searchable="true" placeholder="Τύπος Λιπιδίου"
                                   track-by="id" label="title"
                                   v-validate="rules.lipidType"
                                   :state="isValid('lipidType', 'lipidsForm')"
                                   :class="{'is-invalid': errors.has('lipidType', 'lipidsForm')}">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-4">
                  <b-form-group description="Ποσότητα Ιχνοστοιχείου (γραμμάρια)"
                                :feedback="errors.first('quantity', 'lipidsForm')"
                                :state="isValid('quantity', 'lipidsForm')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='icon-speedometer'></i>
                      </b-input-group-addon>
                      <b-form-input data-vv-scope="lipidsForm" type="number" name="quantity" id="quantity"
                                    v-model="lipidQuantity" v-validate="rules.quantity"
                                    :state="isValid('quantity', 'lipidsForm')"
                                    :class="{ 'is-invalid': errors.has('quantity', 'lipidsForm')}">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-2">
                  <b-button variant="primary" @click="addLipid" class="pull-right" :disabled="errors.any('lipidsForm')"><i class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </div>

              <b-table striped hover :items="lipids" :fields="lipidsFields">
                <!-- virtual index column -->
                <template slot="index" slot-scope="data">
                  {{data.index + 1}}
                </template>
                <template slot="lipidType" slot-scope="data">
                  {{data.value.title}}
                </template>
                <template slot="quantity" slot-scope="data">
                  {{data.value}}
                </template>
                <template slot="actions" slot-scope="row">
                  <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                  <b-button variant="danger" @click.stop="confirmRemoveLipid(row.item)"><i
                    class="fa fa-remove"></i></b-button>
                </template>
              </b-table>
            </b-tab>

            <b-tab title="Λοιπά">
              <div class="row">
                <div class="col-6">
                  <b-form-group description="Επιλέξτε Τύπο Λοιπών"
                                :feedback="errors.first('otherNutrientType', 'otherNutrientsForm')"
                                :state="isValid('otherNutrientType', 'otherNutrientsForm')">
                    <b-input-group>
                      <multiselect data-vv-scope="otherNutrientsForm" name="otherNutrientType" id="otherNutrientType" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                                   v-model="otherNutrientType" :options="otherNutrientTypes"
                                   :searchable="true" placeholder="Τύπος Λοιπών"
                                   track-by="id" label="title"
                                   v-validate="rules.otherNutrientType"
                                   :state="isValid('otherNutrientType', 'otherNutrientsForm')"
                                   :class="{'is-invalid': errors.has('otherNutrientType', 'otherNutrientsForm')}">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-4">
                  <b-form-group description="Ποσότητα Ιχνοστοιχείου (γραμμάρια)"
                                :feedback="errors.first('quantity', 'otherNutrientsForm')"
                                :state="isValid('quantity', 'otherNutrientsForm')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='icon-speedometer'></i>
                      </b-input-group-addon>
                      <b-form-input data-vv-scope="otherNutrientsForm" type="number" name="quantity" id="quantity"
                                    v-model="otherNutrientQuantity" v-validate="rules.quantity"
                                    :state="isValid('quantity', 'otherNutrientsForm')"
                                    :class="{ 'is-invalid': errors.has('quantity', 'otherNutrientsForm')}">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>
                </div>

                <div class="col-2">
                  <b-button variant="primary" @click="addOtherNutrient" class="pull-right" :disabled="errors.any('otherNutrientsForm')"><i class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </div>

              <b-table striped hover :items="otherNutrients" :fields="otherNutrientsFields">
                <!-- virtual index column -->
                <template slot="index" slot-scope="data">
                  {{data.index + 1}}
                </template>
                <template slot="otherNutrientType" slot-scope="data">
                  {{data.value.title}}
                </template>
                <template slot="quantity" slot-scope="data">
                  {{data.value}}
                </template>
                <template slot="actions" slot-scope="row">
                  <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                  <b-button variant="danger" @click.stop="confirmRemoveOtherNutrient(row.item)"><i
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
