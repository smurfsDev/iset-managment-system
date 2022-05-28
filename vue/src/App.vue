<template>
  <div id="app" style="max-height: 100vh;
overflow-y: scroll;">
    <top-nav />
    <div id="nav">
<router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-link to="/demandeMateriel"> demandeMateriel</router-link>
      <router-link to="/members"> members</router-link>
      <router-link to="/dashboard"> dashboardClub</router-link>
      <router-link to="/club/demandeEvent"> demandeEvent</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
* {
  font-size: 14px !important;
}

/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
} */

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
