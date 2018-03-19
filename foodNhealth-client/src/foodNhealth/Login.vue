<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">

          <div class="card-group mb-0">
            <div class="card p-4">
              <div class="card-body">

                <p class="text-muted">Συνδεθείτε στο λογαριασμό σας</p>

                <b-form @submit.prevent="login">
                  <b-form-group :feedback="errors.first('email')"
                                :state="isValid('email')">
                    <b-input-group>
                      <b-input-group-addon>
                        <i class='fa fa-envelope'></i>
                      </b-input-group-addon>
                      <b-form-input name="email" type="email" placeholder="Email" v-model="email" v-validate="rules.email"
                                    :state="isValid('email')" autofocus>
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>

                  <b-form-group :feedback="errors.first('password')"
                                :state="isValid('password')">
                    <b-input-group>
                      <b-input-group-addon><i class="icon-lock"></i>
                      </b-input-group-addon>
                      <b-form-input name="password" type="password" placeholder="Συνθηματικό Χρήστη" v-model="password" v-validate="rules.password"
                                    :state="isValid('password')">
                      </b-form-input>
                    </b-input-group>
                  </b-form-group>

                  <div class="row">
                    <div class="col-6">
                      <button type="submit" class="btn btn-primary px-4">Σύνδεση</button>
                    </div>
                  </div>
                </b-form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./LoginVM.js"></script>
