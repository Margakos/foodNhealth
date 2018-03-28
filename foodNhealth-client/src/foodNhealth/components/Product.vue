<template>
  <div>
    <b-modal title="Διαχείριση Προϊόντος" id="productModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
      <b-form :novalidate="false">

        <div class="row">
          <div class="col-lg-12">
            <!-- Title -->
            <b-form-group description="Εισάγετε το Όνομα Προϊόντος"
                          :feedback="errors.first('name', 'generalForm')"
                          :state="isValid('name', 'generalForm')">
              <b-input-group>
                <b-input-group-addon>
                  <i class='fa fa-tag'></i>
                </b-input-group-addon>
                <b-form-input data-vv-scope="generalForm" name="name" type="text" placeholder="Τίτλος"
                              v-model="product.name"
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
            <!-- Ingredient -->
            <b-form-group description="Επιλέξτε Συστατικό"
                          :feedback="errors.first('ingredient', 'generalForm')"
                          :state="isValid('ingredient', 'generalForm')">
              <b-input-group>
                <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                             data-vv-scope="generalForm" name="ingredient" id="ingredient"
                             v-model="product.ingredient" :options="ingredients"
                             :searchable="true" placeholder="Συστατικό"
                             track-by="id" label="name"
                             v-validate="rules.ingredient"
                             :state="isValid('ingredient', 'generalForm')"
                             :class="{'is-invalid': errors.has('ingredient', 'generalForm')}">
                </multiselect>
              </b-input-group>
            </b-form-group>

            <div class="form action text-right">
              <b-button size="sm" variant="success" @click="save" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
              <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
            </div>
          </b-tab>

          <b-tab title="Συσκευασίες" :disabled="product.id == null">
            <div class="col-lg-12">
              <b-card border-variant="light">
                <b-table striped hover :items="productPackages" :fields="productPackagesFields" @row-clicked="editProductPackage">
                  <!-- virtual index column -->
                  <template slot="index" slot-scope="data">
                    {{data.index + 1}}
                  </template>
                  <template slot="title" slot-scope="data">
                    {{data.value}}
                  </template>
                  <template slot="quantity" slot-scope="data">
                    {{formatGrams(data.value)}}
                  </template>
                  <template slot="price" slot-scope="data">
                    {{formatAmount(data.value)}}
                  </template>
                  <template slot="supermarket" slot-scope="data">
                    {{data.value != null ? data.value.title : "-"}}
                  </template>
                </b-table>

                <div slot="footer">
                  <b-button variant="primary" class="pull-right" @click="createProductPackage"><i
                    class="fa fa-plus"></i>
                    Προσθήκη
                  </b-button>
                </div>
              </b-card>
            </div>
          </b-tab>

          <b-tab title="Θρεπτικά Συστατικά" :disabled="product.id == null">
            <b-tabs pills vertical card>

              <b-tab title="Θρεπτικά Συστατικά" active>
                <div class="row">

                  <b-table striped hover :items="productProximates" :fields="productProximatesFields">
                    <!-- virtual index column -->
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="productProximateType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      <div class="col-8 center">
                        <b-form-group description="Ποσότητα Θρεπτικού Συστατικού (grams)"
                                      :feedback="errors.first('quantity_' + productProximates[data.index].id, 'productProximatesForm_' + data.index)"
                                      :state="isValid('quantity_' + productProximates[data.index].id, 'productProximatesForm_' + data.index)">
                          <b-form-input :data-vv-scope="'productProximatesForm_' + data.index" type="number" :name="'quantity_' + productProximates[data.index].id" :id="'quantity_' + productProximates[data.index].id"
                                        v-model="productProximates[data.index].quantity" :value="data.value"
                                        v-validate="rules.quantity"
                                        :state="isValid('quantity_' + productProximates[data.index].id, 'productProximatesForm_' + data.index)"
                                        :class="{'is-invalid': errors.has('quantity_' + productProximates[data.index].id, 'productProximatesForm_' + data.index)}">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </template>
                    <template slot="actions" slot-scope="row">
                      <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                      <b-button variant="success" @click.stop="updateProximate(row.item)" :disabled="errors.any('productProximatesForm_' + row.index) || product.id == null"><i
                        class="fa fa-check"></i></b-button>
                    </template>
                  </b-table>
                </div>

              </b-tab>

              <b-tab title="Ιχνοστοιχεία">
                <div class="row">
                  <b-table striped hover :items="productMinerals" :fields="productMineralsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="productMineralType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      <div class="col-8 center">
                        <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                      :feedback="errors.first('quantity_' + productMinerals[data.index].id, 'productMineralsForm_' + data.index)"
                                      :state="isValid('quantity_' + productMinerals[data.index].id, 'productMineralsForm_' + data.index)">
                          <b-form-input :data-vv-scope="'productMineralsForm_' + data.index" type="number" :name="'quantity_' + productMinerals[data.index].id" :id="'quantity_' + productMinerals[data.index].id"
                                        v-model="productMinerals[data.index].quantity" :value="data.value"
                                        v-validate="rules.quantity"
                                        :state="isValid('quantity_' + productMinerals[data.index].id, 'productMineralsForm_' + data.index)"
                                        :class="{'is-invalid': errors.has('quantity_' + productMinerals[data.index].id, 'productMineralsForm_' + data.index)}">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </template>
                    <template slot="actions" slot-scope="row">
                       <!--We use click.stop here to prevent a 'row-clicked' event from also happening-->
                      <b-button variant="success" @click.stop="updateMineral(row.item)" :disabled="errors.any('productMineralsForm_' + row.index) || product.id == null"><i
                        class="fa fa-check"></i></b-button>
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Βιταμίνες">
                <div class="row">
                  <b-table striped hover :items="productVitamins" :fields="productVitaminsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="productVitaminType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      <div class="col-8 center">
                        <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                      :feedback="errors.first('quantity_' + productVitamins[data.index].id, 'productVitaminsForm_' + data.index)"
                                      :state="isValid('quantity_' + productVitamins[data.index].id, 'productVitaminsForm_' + data.index)">
                          <b-form-input :data-vv-scope="'productVitaminsForm_' + data.index" type="number" :name="'quantity_' + productVitamins[data.index].id" :id="'quantity_' + productVitamins[data.index].id"
                                        v-model="productVitamins[data.index].quantity" :value="data.value"
                                        v-validate="rules.quantity"
                                        :state="isValid('quantity_' + productVitamins[data.index].id, 'productVitaminsForm_' + data.index)"
                                        :class="{'is-invalid': errors.has('quantity_' + productVitamins[data.index].id, 'productVitaminsForm_' + data.index)}">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </template>
                    <template slot="actions" slot-scope="row">
                       We use click.stop here to prevent a 'row-clicked' event from also happening
                      <b-button variant="success" @click.stop="updateVitamin(row.item)" :disabled="errors.any('productVitaminsForm_' + row.index) || product.id == null"><i
                        class="fa fa-check"></i></b-button>
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Λιπίδια">
                <div class="row">
                  <b-table striped hover :items="productLipids" :fields="productLipidsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="productLipidType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      <div class="col-8 center">
                        <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                      :feedback="errors.first('quantity_' + productLipids[data.index].id, 'productLipidsForm_' + data.index)"
                                      :state="isValid('quantity_' + productLipids[data.index].id, 'productLipidsForm_' + data.index)">
                          <b-form-input :data-vv-scope="'productLipidsForm_' + data.index" type="number" :name="'quantity_' + productLipids[data.index].id" :id="'quantity_' + productLipids[data.index].id"
                                        v-model="productLipids[data.index].quantity" :value="data.value"
                                        v-validate="rules.quantity"
                                        :state="isValid('quantity_' + productLipids[data.index].id, 'productLipidsForm_' + data.index)"
                                        :class="{'is-invalid': errors.has('quantity_' + productLipids[data.index].id, 'productLipidsForm_' + data.index)}">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </template>
                    <template slot="actions" slot-scope="row">
                       We use click.stop here to prevent a 'row-clicked' event from also happening
                      <b-button variant="success" @click.stop="updateLipid(row.item)" :disabled="errors.any('productLipidsForm_' + row.index) || product.id == null"><i
                        class="fa fa-check"></i></b-button>
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Λοιπά">
                <div class="row">
                  <b-table striped hover :items="productOtherNutrients" :fields="productOtherNutrientsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="productOtherNutrientType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      <div class="col-8 center">
                        <b-form-group description="Ποσότητα Ιχνοστοιχείου (grams)"
                                      :feedback="errors.first('quantity_' + productOtherNutrients[data.index].id, 'productOtherNutrientsForm_' + data.index)"
                                      :state="isValid('quantity_' + productOtherNutrients[data.index].id, 'productOtherNutrientsForm_' + data.index)">
                          <b-form-input :data-vv-scope="'productOtherNutrientsForm_' + data.index" type="number" :name="'quantity_' + productOtherNutrients[data.index].id" :id="'quantity_' + productOtherNutrients[data.index].id"
                                        v-model="productOtherNutrients[data.index].quantity" :value="data.value"
                                        v-validate="rules.quantity"
                                        :state="isValid('quantity_' + productOtherNutrients[data.index].id, 'productOtherNutrientsForm_' + data.index)"
                                        :class="{'is-invalid': errors.has('quantity_' + productOtherNutrients[data.index].id, 'productOtherNutrientsForm_' + data.index)}">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </template>
                    <template slot="actions" slot-scope="row">
                       We use click.stop here to prevent a 'row-clicked' event from also happening
                      <b-button variant="success" @click.stop="updateOtherNutrient(row.item)" :disabled="errors.any('productOtherNutrientsForm_' + row.index) || product.id == null"><i
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
    <productPackage></productPackage>
  </div>
</template>

<script src="./ProductVM.js"></script>
