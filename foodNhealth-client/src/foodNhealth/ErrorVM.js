export default {
  name: 'Error',
  components: {},
  data: function () {
    return {
      code: null
    }
  },
  created () {
    if (this.$route.params.code != null) {
      this.code = this.$route.params.code
    }
    console.log('Error created')
  },
  mounted () {
    console.log('Error mounted')
  },
  destroyed: function () {
    console.log('Error destroyed')
  },
  computed: {
    message () {
      switch (this.code) {
        case '500':
          return this.$messages.errorCommunication
        case '401':
          return this.$messages.errorUnauthorized
        case '403':
          return this.$messages.errorForbidden
        default:
          return this.$messages.errorUnknown
      }
    }
  },
  methods: {}
}
