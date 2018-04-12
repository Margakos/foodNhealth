<template>
  <b-modal title="Διαχείριση Ατόμου" id="clientModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
    <b-form :novalidate="false">

      <b-alert variant="info" v-show="client.email === ''">
        <i class="fa fa-info-circle"></i>
        Επιλέξτε email ατόμου προκειμένου να το προσθέσετε στη λίστα σας
      </b-alert>

      <div class="row">
        <div class="col-lg-12">
          <!-- Client -->
          <b-form-group description="Πληκτρολογήστε το Χρήστη"
                        :feedback="errors.first('email', 'generalForm')"
                        :state="isValid('email', 'generalForm')">
            <multiselect data-vv-scope="generalForm" id="client" name="client"
                         v-model="client" track-by="id" placeholder="Χρήστης"
                         :custom-label="clientCustomLabel"
                         open-direction="bottom" :options="clients" :multiple="false"
                         :searchable="true" :loading="isLoading" :internal-search="false"
                         :close-on-select="true" :options-limit="50"
                         v-validate="rules.client"
                         :max-height="600" :show-no-results="true" :hide-selected="true" @search-change="searchClients"
                         :class="{'is-invalid': errors.has('email', 'generalForm')}">
              <span slot="noResult">Δε βρέθηκαν αποτελέσματα, δοκιμάστε μια διαφορετική αναζήτηση</span>
            </multiselect>
          </b-form-group>
        </div>
      </div>

      <b-tabs pills>
        <b-tab title="Γενικά Στοιχεία" active>
          <b-card border-variant="light" v-show="client.email !== null">
            <div class="row">
              <!--LEFT COLUMN-->
              <div class="col-6">
                <!-- First Name -->
                <b-form-group description="Όνομα" :feedback="errors.first('firstName', 'generalForm')"
                              :state="isValid('firstName', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-user'></i>
                    </b-input-group-addon>
                    <b-form-input data-vv-scope="generalForm" name="firstName" type="text" placeholder="Όνομα" v-model="client.firstName"
                                  v-validate="rules.firstName" :state="isValid('firstName', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>

                <!-- Last Name -->
                <b-form-group description="Επώνυμο" :feedback="errors.first('lastName', 'generalForm')"
                              :state="isValid('lastName', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-address-card-o'></i>
                    </b-input-group-addon>
                    <b-form-input data-vv-scope="generalForm" name="lastName" type="text" placeholder="Επώνυμο" v-model="client.lastName"
                                  v-validate="rules.lastName" :state="isValid('lastName', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>

                <!-- Email -->
                <b-form-group description="Διεύθυνση Email" :feedback="errors.first('email', 'generalForm')"
                              :state="isValid('email', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-envelope'></i>
                    </b-input-group-addon>
                    <b-form-input data-vv-scope="generalForm" name="email" type="email" placeholder="Email" v-model="client.email"
                                  v-validate="rules.email"
                                  :state="isValid('email', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>

                <!-- Gender -->
                <b-form-group>
                  Φύλο: <strong>{{!(client.gender == null || client.gender === '') ? (client.gender === 'MALE' ? getMessage('MALE') : getMessage('FEMALE')) : '-'}}</strong>
                </b-form-group>
              </div>

              <!--RIGHT COLUMN-->
              <div class="col-6">
                <!-- Address -->
                <b-form-group description="Διεύθυνση" :feedback="errors.first('address', 'generalForm')"
                              :state="isValid('address', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-map-marker'></i>
                    </b-input-group-addon>
                    <b-form-input data-vv-scope="generalForm" name="address" type="text" placeholder="Διεύθυνση" v-model="client.address"
                                  v-validate="rules.address" :state="isValid('address', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>

                <!-- City -->
                <b-form-group description="Πόλη" :feedback="errors.first('city', 'generalForm')"
                              :state="isValid('city', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-building'></i>
                    </b-input-group-addon>
                    <b-form-input data-vv-scope="generalForm" name="city" type="text" placeholder="Πόλη" v-model="client.city"
                                  v-validate="rules.city" :state="isValid('city', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>

                <!-- Zip Code -->
                <b-form-group data-vv-scope="generalForm" description="Ταχυδρομικός Κώδικας" :feedback="errors.first('zipCode', 'generalForm')"
                              :state="isValid('zipCode', 'generalForm')">
                  <b-input-group>
                    <b-input-group-addon>
                      <i class='fa fa-map-signs'></i>
                    </b-input-group-addon>
                    <b-form-input name="zipCode" type="text" placeholder="Ταχυδρομικός Κώδικας" v-model="client.zipCode"
                                  v-validate="rules.zipCode" :state="isValid('zipCode', 'generalForm')" :readonly="true"></b-form-input>
                  </b-input-group>
                </b-form-group>
              </div>
            </div>
          </b-card>
          <div class="form action text-right">
            <b-button  size="sm" variant="success" @click="save" v-show="!isDeletable" :disabled="errors.any('generalForm')"><i
              class="fa fa-dot-circle-o"></i> Προσθήκη
            </b-button>
            <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Αφαίρεση</b-button>
          </div>
        </b-tab>

        <b-tab title="Προτιμήσεις" :disabled="!insertionMode">
          <div class="row">
            <div class="col-12">
              <!-- Disliked Ingredients -->
              <b-form-group description="Πληκτρολογήστε τα Ανεπιθύμητα Συστατικά"
                            :feedback="errors.first('dislikedIngredients', 'preferenceForm')"
                            :state="isValid('dislikedIngredients', 'preferenceForm')">
                <multiselect data-vv-scope="preferenceForm" id="dislikedIngredients" name="dislikedIngredients"
                             v-model="preference.dislikedIngredients"  placeholder="Ανεπιθύμητα Συστατικά"
                             label="title" track-by="id"
                             open-direction="bottom" :options="dislikedIngredients" :multiple="false"
                             :searchable="true" :loading="isLoading" :internal-search="false"
                             :close-on-select="true" :options-limit="50"
                             v-validate="rules.client"
                             :max-height="600" :show-no-results="true" :hide-selected="true" @search-change="searchIngredients"
                             :class="{'is-invalid': errors.has('dislikedIngredients', 'preferenceForm')}">
                  <span slot="noResult">Δε βρέθηκαν αποτελέσματα, δοκιμάστε μια διαφορετική αναζήτηση</span>
                </multiselect>
              </b-form-group>

              <!-- Allergies -->
              <b-form-group description="Επιλέξτε τις κατηγορίες Αλλεργίας"
                            :feedback="errors.first('allergies', 'preferenceForm')"
                            :state="isValid('allergies', 'preferenceForm')">
                <b-input-group>
                  <multiselect :selected-label="$messages.selected" :deselect-label="$messages.removeSelection" :select-label="$messages.setSelection"
                               data-vv-scope="preferenceForm" name="allergies" id="allergies"
                               v-model="preference.allergies" :options="allergies"
                               :multiple="true" :hideSelected="true"
                               :searchable="true" placeholder="Αλλεργίες"
                               track-by="id" label="title"
                               v-validate="rules.allergies"
                               :state="isValid('allergies', 'preferenceForm')"
                               :class="{'is-invalid': errors.has('allergies', 'preferenceForm')}">
                  </multiselect>
                </b-input-group>
              </b-form-group>

              <!-- Weight Per Week -->
              <b-form-group description="Στόχος Απώλειας Βάρους"
                            :feedback="errors.first('weightPerWeek', 'preferenceForm')"
                            :state="isValid('weightPerWeek', 'preferenceForm')">
                <b-form-input data-vv-scope="preferenceForm" type="number" name="weightPerWeek" id="weightPerWeek"
                              v-model="preference.weightPerWeek"
                              v-validate="rules.weightPerWeek"
                              :state="isValid('weightPerWeek', 'preferenceForm')"
                              :class="{'is-invalid': errors.has('weightPerWeek', 'preferenceForm')}">
                </b-form-input>
              </b-form-group>
            </div>
          </div>

          <div class="form action text-right">
            <b-button  size="sm" variant="success" @click="savePreference" :disabled="errors.any('preferenceForm')"><i
              class="fa fa-dot-circle-o"></i> Ενημέρωση
            </b-button>
          </div>
        </b-tab>
      </b-tabs>

    </b-form>

    <div slot="modal-footer">
      <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>

  </b-modal>
</template>

<script src="./ClientVM.js"></script>
