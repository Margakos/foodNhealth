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
                          :feedback="errors.first('cuisines', 'generalForm')"
                          :state="isValid('cuisines', 'generalForm')">
              <b-input-group>
                <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                             data-vv-scope="generalForm" name="cuisines" id="cuisines"
                             v-model="recipe.cuisines" :options="cuisines"
                             :multiple="true" :hideSelected="true"
                             :searchable="true" placeholder="Κουζίνα"
                             track-by="id" label="title"
                             v-validate="rules.cuisines"
                             :state="isValid('cuisines', 'generalForm')"
                             :class="{'is-invalid': errors.has('cuisines', 'generalForm')}">
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

            <!-- Ingredient Portions -->
            <b-table striped hover :items="ingredientPortions" :fields="ingredientPortionsFields">
              <!-- virtual index column -->
              <template slot="index" slot-scope="data">
                {{data.index + 1}}
              </template>
              <template slot="ingredient" slot-scope="data">
                <div v-show="recipe.id !== null">{{(ingredientPortions[data.index] && ingredientPortions[data.index].ingredient) != null ? ingredientPortions[data.index].ingredient.name : '-'}}</div>
                <div class="col-8 center" v-show="recipe.id === null">
                  <!-- Ingredient -->
                  <b-form-group description="Επιλέξτε Συστατικό"
                                :feedback="errors.first('ingredient_' + data.index, 'generalForm_' + data.index)"
                                :state="isValid('ingredient_' + data.index, 'generalForm_' + data.index)">
                    <b-input-group>
                      <multiselect data-vv-scope="generalForm" :selected-label="$messages.selected" :deselect-label="$messages.removeSelection"
                                   :select-label="$messages.setSelection" :name="'ingredient_' + data.index" :id="'ingredient_' + data.index"
                                   v-model="ingredientPortions[data.index].ingredient"
                                   :options="ingredients"
                                   placeholder="Συστατικό"
                                   label="name" track-by="id" v-validate="rules.ingredient"
                                   :state="isValid('ingredient_' + data.index, 'generalForm_' + data.index)"
                                   :class="{'is-invalid': errors.has('ingredient_' + data.index, 'generalForm_' + data.index)}"
                                   :disabled="recipe.id !== null" @input="ingredientChanged(data.index)">
                      </multiselect>
                    </b-input-group>
                  </b-form-group>
                </div>
              </template>
              <template slot="quantity_slices" slot-scope="data">
                <div v-show="recipe.id !== null && quantityShown[data.index]">{{ingredientPortions[data.index] != null ? ingredientPortions[data.index].quantity + '  gr' : '-'}}</div>
                <div v-show="recipe.id !== null && piecesShown[data.index]">{{ingredientPortions[data.index] != null ? ingredientPortions[data.index].pieces + '  τεμάχια' : '-'}}</div>
                <div class="col-8 center" v-show="recipe.id === null">
                  <!-- Quantity -->
                  <b-form-group description="Ποσότητα Συστατικού (grams)"
                                :feedback="errors.first('quantity_' + data.index, 'generalForm_' + data.index)"
                                :state="isValid('quantity_' + data.index, 'generalForm_' + data.index)"
                                v-show="quantityShown[data.index]">
                    <b-form-input :data-vv-scope="'generalForm_' + data.index" type="number" :name="'quantity_' + data.index" :id="'quantity_' + data.index"
                                  v-model="ingredientPortions[data.index].quantity" :value="data.value"
                                  v-validate="rules.quantity"
                                  :state="isValid('quantity_' + data.index, 'generalForm_' + data.index)"
                                  :class="{'is-invalid': errors.has('quantity_' + data.index, 'generalForm_' + data.index)}"
                                  :disabled="!quantityShown[data.index] || recipe.id !== null">
                    </b-form-input>
                  </b-form-group>

                  <!-- Pieces -->
                  <b-form-group description="Κομμάτια Συστατικού"
                                :feedback="errors.first('pieces_' + data.index, 'generalForm_' + data.index)"
                                :state="isValid('pieces_' + data.index, 'generalForm_' + data.index)"
                                v-show="piecesShown[data.index]">
                    <b-form-input :data-vv-scope="'generalForm_' + data.index" type="number" :name="'pieces_' + data.index" :id="'pieces_' + data.index"
                                  v-model="ingredientPortions[data.index].pieces" :value="data.value"
                                  v-validate="rules.pieces"
                                  :state="isValid('pieces_' + data.index, 'generalForm_' + data.index)"
                                  :class="{'is-invalid': errors.has('pieces_' + data.index, 'generalForm_' + data.index)}"
                                  :disabled="!piecesShown[data.index] || recipe.id !== null">
                    </b-form-input>
                  </b-form-group>
                </div>
              </template>
              <template slot="actions" slot-scope="row">
                <!-- We use click.stop here to prevent a 'row-clicked' event from also happening -->
                <b-button variant="danger" @click.stop="deleteIngredientPortion(row.item)" :disabled="errors.any('generalForm_' + row.index) || recipe.id !== null" v-show="recipe.id === null"><i
                  class="fa fa-remove"></i></b-button>
              </template>
            </b-table>
            <b-button class="float-right" size="sm" variant="primary" @click="addIngredientPortion" v-show="recipe.id === null"><i class="fa fa-plus"></i></b-button><br/><br/>

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
        </b-tabs>

      </b-form>

      <div slot="modal-footer">
        <b-button size="sm" variant="warning" @click="cancel" type="reset"><i class="fa fa-ban"></i> Επιστροφή</b-button>
      </div>
    </b-modal>
  </div>
</template>

<script src="./RecipeVM.js"></script>
