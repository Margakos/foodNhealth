<template>
  <b-modal title="Διαχείριση Ατόμου" id="clientModal" v-model="visible" :no-close-on-backdrop="true" :no-close-on-esc="true" size="lg">
    <b-form :novalidate="false">

      <b-alert variant="info" v-show="client.email !== null">
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
        </b-tab>
      </b-tabs>

    </b-form>

    <div slot="modal-footer">
      <b-button  size="sm" variant="success" @click="save" v-show="!isDeletable" :disabled="errors.any('generalForm')"><i
        class="fa fa-dot-circle-o"></i> Προσθήκη
      </b-button>
      <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i> Αφαίρεση</b-button>
      <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>

  </b-modal>
</template>

<script src="./ClientVM.js"></script>
