<template>
  <aside class="aside-menu">
    <b-tabs pills>
      <b-tab title="<i class='fa fa-warning'></i> Ειδοποιήσεις">
        <div class="p-3">
          <div class="message" v-if="notifications.length === 0">
            <small class="text-muted">Δεν υπάρχουν νέες ειδοποιήσεις</small>
          </div>
        </div>
      </b-tab>
    </b-tabs>
  </aside>
</template>

<script src="./AsideVM.js"></script>
