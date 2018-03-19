export default {
  name: 'aside',
  components: {},
  data () {
    return {
      notifications: []
    }
  },
  created () {
    console.log('Aside created')
  },
  mounted () {
    // poll server for new notifications every X minutes
    // setInterval(() => {
    //   let tempNotifications = this.notifications.slice(0)
    //   this.refreshNotifications().then(() => {
    //     if (!Vue._.isEqual(tempNotifications, this.notifications)) {
    //       this.notifyUser()
    //     }
    //   })
    // }, Vue.prototype.$notificationsPollingInterval * 60 * 1000)
    console.log('Aside mounted')
  },
  methods: {
  }
}
