<template>
  <footer class="app-footer">
    <a href="http://www.publicsoft.gr/" target="_blank">PublicSoft </a> &copy; 2017 {{(new Date()).getFullYear() == '2017' ? '' : '- ' + (new Date()).getFullYear()}}
    <!--<span class="float-right">Powered by <a href="http://coreui.io">CoreUI</a></span>-->
  </footer>
</template>
<script>
export default {
  name: 'footer'
}
</script>
