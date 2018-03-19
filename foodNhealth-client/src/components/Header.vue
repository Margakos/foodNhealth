<template>
  <div>
    <header class="app-header navbar">
      <button class="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button" @click="mobileSidebarToggle">
        <span class="navbar-toggler-icon"></span>
      </button>
      <b-link class="navbar-brand" to="calendar"></b-link>
      <button class="navbar-toggler sidebar-toggler d-md-down-none" type="button" @click="sidebarToggle">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!--<b-navbar-nav class="d-md-down-none">-->
      <!--<b-nav-item class="px-3">Dashboard</b-nav-item>-->
      <!--<b-nav-item class="px-3">Users</b-nav-item>-->
      <!--<b-nav-item class="px-3">Settings</b-nav-item>-->
      <!--</b-navbar-nav>-->
      <b-navbar-nav class="ml-auto pr-3">
        <b-nav-item class="d-md-down-none" @click="asideToggle">
          <i class="icon-bell"></i>
          <b-badge pill variant="danger" v-if="notificationsCount > 0">{{notificationsCount}}</b-badge>
        </b-nav-item>
        <!--<b-nav-item class="d-md-down-none">-->
        <!--<i class="icon-list"></i>-->
        <!--</b-nav-item>-->
        <!--<b-nav-item class="d-md-down-none">-->
        <!--<i class="icon-location-pin"></i>-->
        <!--</b-nav-item>-->
        <!--<HeaderDropdown/>-->
        <b-nav-item-dropdown right  v-if="$auth.check()">
          <template slot="button-content">
            <span class="d-md-down-none">{{$auth.user().email}}</span>
          </template>
          <b-dropdown-item @click="changePassword" v-if="!$auth.user().ldapEnabled"><i class="fa fa-gear"></i> Αλλαγή συνθηματικού</b-dropdown-item>
          <b-dropdown-item @click="logout"><i class="fa fa-lock"></i> Αποσύνδεση</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
      <button class="navbar-toggler aside-menu-toggler d-md-down-none" type="button" @click="asideToggle">
        <span class="navbar-toggler-icon"></span>
      </button>
    </header>
    <changePassword v-if="!$auth.user().ldapEnabled"></changePassword>
  </div>
</template>
<script>
  import HeaderDropdown from './HeaderDropdown.vue'

  export default {
    name: 'header',
    components: {
      HeaderDropdown
    },
    data () {
      return {
        notificationsCount: 0
      }
    },
    mounted () {
      this.$events.$on('notifications-refreshed', notifications => {
        this.notificationsCount = notifications.length
      })
    },
    beforeDestroy () {
      this.$events.$off('notifications-refreshed')
    },
    methods: {
      sidebarToggle (e) {
        e.preventDefault()
        document.body.classList.toggle('sidebar-hidden')
      },
      sidebarMinimize (e) {
        e.preventDefault()
        document.body.classList.toggle('sidebar-minimized')
      },
      mobileSidebarToggle (e) {
        e.preventDefault()
        document.body.classList.toggle('sidebar-mobile-show')
      },
      asideToggle (e) {
        e.preventDefault()
        document.body.classList.toggle('aside-menu-hidden')
      },
      changePassword () {
        this.$events.fire('edit-changePassword')
      }
    }
  }
</script>
