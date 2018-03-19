import Vue from 'vue'

export default {
  name: 'Login',
  components: {},
  data: function () {
    return {
      visible: false,
      email: null,
      password: null,
      rules: {
        email: {
          required: true,
          email: true,
          max: 255
        },
        password: {
          required: true,
          max: 255
        }
      }
    }
  },
  created () {
    console.log('Login created')
  },
  mounted () {
    console.log('Login mounted')
  },
  destroyed: function () {
    console.log('Login destroyed')
  },
  computed: {},
  methods: {
    login () {
      this.$validator.validateAll().then((result) => {
        if (!result) {
          // validation failed, nothing special to do
          return
        }
        console.log('logging in')
        let _self = this
        this.$auth.login({
          data: {email: this.email, password: this.password},
          success: function (response) {
            this.$auth.token(null, 'Basic ' + window.btoa(_self.email + ':' + _self.password))
            this.$auth.user(response.data)
          },
          error: function (response) {
            _self.error(Vue.prototype.$messages.errorLogin)
          },
          rememberMe: false
        })
      })
    },
    transformRequest (data) {
      data.department = this.convertEntityToURI(data.department)
      data.roles = Vue._.map(data.roles, function (role) {
        return role._links.self.href
      })
      return JSON.stringify(data)
    }
  }
}
