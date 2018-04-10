<template>
  <b-modal title="Διαχείριση Χρήστη" id="nutritionistModal" v-model="visible" :no-close-on-backdrop="true"
           :no-close-on-esc="true" size="lg">
    <b-form :novalidate="false">


      <div class="row">
        <!--LEFT COLUMN-->
        <div class="col-6">

          <b-form-group description="Εισάγετε το όνομα" :feedback="errors.first('firstName')"
                        :state="isValid('firstName')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-user'></i>
              </b-input-group-addon>
              <b-form-input name="firstName" type="text" placeholder="Όνομα" v-model="nutritionist.firstName"
                            v-validate="rules.firstName" :state="isValid('firstName')"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group description="Εισάγετε το επώνυμο" :feedback="errors.first('lastName')"
                        :state="isValid('lastName')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-vcard-o'></i>
              </b-input-group-addon>
              <b-form-input name="lastName" type="text" placeholder="Επώνυμο" v-model="nutritionist.lastName"
                            v-validate="rules.lastName" :state="isValid('lastName')"></b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group description="Εισάγετε τη διεύθυνση Email" :feedback="errors.first('email')"
                        :state="isValid('email')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-envelope'></i>
              </b-input-group-addon>
              <b-form-input name="email" type="email" placeholder="Email" v-model="nutritionist.email"
                            v-validate="rules.email"
                            :state="isValid('email')">
              </b-form-input>
            </b-input-group>
          </b-form-group>

          <b-form-group description="Εισάγετε το συνθηματικό εισόδου (εφόσον επιθυμείτε να το θέσετε/αλλάξετε)"
                        :feedback="errors.first('password')"
                        :state="isValid('password')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-question'></i>
              </b-input-group-addon>
              <b-form-input name="password" type="password" placeholder="Συνθηματικό Χρήστη" v-model="nutritionist.password"
                            v-validate="rules.password"
                            :state="isValid('password')">
              </b-form-input>
            </b-input-group>
          </b-form-group>

        </div>

        <!--RIGHT COLUMN-->
        <div class="col-6">
          <b-form-group label="Ενεργός" :feedback="errors.first('isActive')" :state="isValid('isActive')"
                        :horizontal="true">
            <b-input-group>
              <b-form-checkbox name="isActive" v-model="nutritionist.isActive" v-validate="rules.isActive"
                               :state="isValid('isActive')">
              </b-form-checkbox>
            </b-input-group>
          </b-form-group>

          <b-form-group description="Συμπληρώστε προαιρετικά σχόλια" :feedback="errors.first('comments')"
                        :state="isValid('comments')">
            <b-input-group>
              <b-input-group-addon>
                <i class='fa fa-comment-o'></i>
              </b-input-group-addon>
              <b-form-textarea name="comments" placeholder="Σχόλια" v-model="nutritionist.comments"
                               v-validate="rules.comments" :state="isValid('comments')" :rows="2"
                               :maxRows="2"></b-form-textarea>
            </b-input-group>
          </b-form-group>

        </div>
      </div>

    </b-form>

    <div slot="modal-footer">
      <b-button size="sm" variant="success" @click="save" :disabled="errors.any()"><i
        class="fa fa-dot-circle-o"></i> Αποθήκευση
      </b-button>
      <b-button size="sm" variant="danger" @click="confirmDelete" v-show="isDeletable"><i class="fa fa-remove"></i>
        Διαγραφή
      </b-button>
      <b-button type="reset" size="sm" variant="warning" @click="cancel"><i class="fa fa-ban"></i> Επιστροφή</b-button>
    </div>
  </b-modal>
</template>
<script src="./NutritionistVM.js"></script>
