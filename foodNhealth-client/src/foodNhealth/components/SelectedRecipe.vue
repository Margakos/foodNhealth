<template>
  <div>
    <b-modal title="Διαχείριση Συνταγής" id="selectedRecipeModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
      <b-form :novalidate="false">

        <div class="row">
          <div class="col-lg-12">
            <!-- Title -->
            <b-form-group description="Εισάγετε το Όνομα Συνταγής"
                          :feedback="errors.first('name', 'generalForm')"
                          :state="isValid('name', 'generalForm')">
              <b-input-group>
                <b-input-group-addon>
                  <i class='fa fa-tag'></i>
                </b-input-group-addon>
                <b-form-input data-vv-scope="generalForm" name="name" type="text" placeholder="Όνομα Συνταγής"
                              v-model="selectedRecipe.name"
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
            <!-- Recipe -->
            <b-form-group description="Επιλέξτε Συνταγή"
                          :feedback="errors.first('recipe', 'generalForm')"
                          :state="isValid('recipe', 'generalForm')">
              <b-input-group>
                <multiselect data-vv-scope="generalForm" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection"
                             :select-label="$messages.setSelection" name="recipe" id="recipe"
                             v-model="selectedRecipe.recipe"
                             :options="recipes"
                             placeholder="Συνταγή"
                             label="name" track-by="id" v-validate="rules.recipe"
                             :state="isValid('recipe', 'generalForm')"
                             :class="{'is-invalid': errors.has('recipe', 'generalForm')}"
                             :disabled="selectedRecipe.id !== null" @input="recipeChanged">
                </multiselect>
              </b-input-group>
            </b-form-group>


            <table class="table table-striped" style="width:100%" v-show="selectedRecipe.recipe">
              <thead>
              <tr>
                <th>Α/Α</th>
                <th>Συστατικό</th>
                <th>Προϊόν</th>
                <th>Συσκευασία</th>
              </tr>
              </thead>

              <tbody>
                <tr v-for="(selectedRecipeRow, index) in selectedRecipeRows">
                  <td>{{index + 1}}</td>
                  <td>
                    <div>{{(selectedRecipeRow.products[0].ingredient) ? selectedRecipeRow.products[0].ingredient.name : '-'}}</div>
                  </td>
                  <td>
                    <!-- Product -->
                    <b-form-group description="Επιλέξτε Προϊόν"
                                  :feedback="errors.first('product_' + index, 'generalForm')"
                                  :state="isValid('product_' + index, 'generalForm')">
                      <b-input-group>
                        <multiselect data-vv-scope="generalForm" :showLabels="false" :name="'productPackage_' + index" :id="'productPackage_' + index"
                                     v-model="selectedRecipeRow.product"
                                     :options="selectedRecipeRow.products"
                                     placeholder="Προϊόν"
                                     label="name" track-by="id" v-validate="rules.product"
                                     :state="isValid('product_' + index, 'generalForm')"
                                     :class="{'is-invalid': errors.has('product_' + index, 'generalForm')}"
                                     @input="productChanged(selectedRecipeRow)">
                        </multiselect>
                      </b-input-group>
                    </b-form-group>
                  </td>
                  <td>
                    <!-- Product Package -->
                    <b-form-group description="Επιλέξτε Συσκευασία"
                                  :feedback="errors.first('productPackage_' + index, 'generalForm')"
                                  :state="isValid('productPackage_' + index, 'generalForm')">
                      <b-input-group>
                        <multiselect data-vv-scope="generalForm" :showLabels="false" :name="'productPackage_' + index" :id="'productPackage_' + index"
                                     v-model="selectedRecipeRow.productPackage"
                                     :options="selectedRecipeRow.productPackages"
                                     placeholder="Συσκευασία"
                                     :custom-label="customPackageLabel" track-by="id" v-validate="rules.productPackage"
                                     :state="isValid('productPackage_' + index, 'generalForm')"
                                     :class="{'is-invalid': errors.has('productPackage_' + index, 'generalForm')}"
                                     :disabled="selectedRecipeRow.product === null">
                        </multiselect>
                      </b-input-group>
                    </b-form-group>
                  </td>
                </tr>
              </tbody>
            </table>

            <div class="form action text-right">
              <b-button size="sm" variant="success" @click="beforeSave" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
              <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
            </div>
          </b-tab>

          <b-tab title="Θρεπτικά Συστατικά" >
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
                      {{data.value}}
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
                      {{data.value}}
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
                      {{data.value}}
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
                      {{data.value}}
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
                      {{data.value}}
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
  </div>
</template>

<script src="./SelectedRecipeVM.js"></script>
