<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="clearfix">
            <h1 class="float-left display-3 mr-4">{{code}}</h1>
            <h4 class="pt-3">Σφάλμα Εφαρμογής!</h4>
            <p class="text-muted">{{message}}</p>
          </div>
          Πιέστε <b-link href="#/pages/login">Εδώ</b-link> για να μεταβείτε στη σελίδα σύνδεσης στην εφαρμογή.
        </div>
      </div>
    </div>
  </div>
</template>
<script src="./ErrorVM.js"></script>
