<template>
  <div>
    <b-modal title="Διαχείριση Συνταγής" id="recipeModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
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
                              v-model="recipe.name"
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
            <!-- Cuisine -->
            <b-form-group description="Επιλέξτε την Κουζίνα που ανήκει η Συνταγή"
                          :feedback="errors.first('cuisine', 'generalForm')"
                          :state="isValid('cuisine', 'generalForm')">
              <b-input-group>
                <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                             data-vv-scope="generalForm" name="cuisine" id="cuisine"
                             v-model="recipe.cuisine" :options="cuisines"
                             :searchable="true" placeholder="Κουζίνα"
                             track-by="id" label="title"
                             v-validate="rules.cuisine"
                             :state="isValid('cuisine', 'generalForm')"
                             :class="{'is-invalid': errors.has('cuisine', 'generalForm')}">
                </multiselect>
              </b-input-group>
            </b-form-group>

            <!-- Recipe Category -->
            <b-form-group description="Επιλέξτε την Κατηγορία που ανήκει η Συνταγή"
                          :feedback="errors.first('recipeCategory', 'generalForm')"
                          :state="isValid('recipeCategory', 'generalForm')">
              <b-input-group>
                <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                             data-vv-scope="generalForm" name="recipeCategory" id="recipeCategory"
                             v-model="recipe.recipeCategory" :options="recipeCategories"
                             :searchable="true" placeholder="Κατηγορία"
                             track-by="id" label="title"
                             v-validate="rules.recipeCategory"
                             :state="isValid('recipeCategory', 'generalForm')"
                             :class="{'is-invalid': errors.has('recipeCategory', 'generalForm')}">
                </multiselect>
              </b-input-group>
            </b-form-group>

            <!-- Products-->
            <b-form-group description="Επιλέξτε Προϊόντα"
                          :feedback="errors.first('products', 'generalForm')"
                          :state="isValid('products', 'generalForm')">
              <b-input-group>
                <multiselect data-vv-scope="generalForm" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection"
                             :select-label="$messages.setSelection" name="studySections" id="studySections"
                             :multiple="true"
                             v-model="recipe.products"
                             :options="products"
                             :searchable="true" placeholder="Προϊόντα"
                             label="name" track-by="id" v-validate="rules.products"
                             :state="isValid('products', 'generalForm')"
                             :class="{'is-invalid': errors.has('products', 'generalForm')}"
                             :disabled="recipe.id !== null">
                </multiselect>
              </b-input-group>
            </b-form-group>

            <!-- Instruction -->
            <b-form-group description="Εισάγετε την εκτέλεση της Συνταγής"
                          :feedback="errors.first('instruction', 'generalFrom')"
                          :state="isValid('instruction', 'generalFrom')">
              <b-input-group>
                <b-input-group-addon>
                  <i class='fa fa-file-text'></i>
                </b-input-group-addon>
                <b-form-textarea data-vv-scope="generalForm" name="description" type="text" placeholder="Εκτέλεση Συνταγής"
                                 v-model="recipe.instruction"
                                 v-validate="rules.instruction"
                                 :state="isValid('instruction', 'generalFrom')"
                                 :rows="4"
                                 :maxRows="50">
                </b-form-textarea>
              </b-input-group>
            </b-form-group>

            <div class="form action text-right">
              <b-button size="sm" variant="success" @click="save" :disabled="errors.any('generalForm')"><i class="fa fa-dot-circle-o"></i> Αποθήκευση</b-button>
              <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Διαγραφή</b-button>
            </div>
          </b-tab>

          <b-tab title="Θρεπτικά Συστατικά" :disabled="recipe.id == null">
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
                    <template slot="unit" slot-scope="data">
                      gr
                    </template>
                  </b-table>
                </div>

              </b-tab>

              <b-tab title="Ιχνοστοιχεία">
                <div class="row">
                  <b-table striped hover :items="minerals" :fields="mineralsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="mineralType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="unit" slot-scope="data">
                      gr
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Βιταμίνες">
                <div class="row">
                  <b-table striped hover :items="vitamins" :fields="vitaminsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="vitaminType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="unit" slot-scope="data">
                      gr
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Λιπίδια">
                <div class="row">
                  <b-table striped hover :items="lipids" :fields="lipidsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="lipidType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="unit" slot-scope="data">
                      gr
                    </template>
                  </b-table>
                </div>
              </b-tab>

              <b-tab title="Λοιπά">
                <div class="row">
                  <b-table striped hover :items="otherNutrients" :fields="otherNutrientsFields">
                     virtual index column
                    <template slot="index" slot-scope="data">
                      {{data.index + 1}}
                    </template>
                    <template slot="otherNutrientType" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="quantity" slot-scope="data">
                      {{data.value}}
                    </template>
                    <template slot="unit" slot-scope="data">
                      gr
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

<script src="./RecipeVM.js"></script>
