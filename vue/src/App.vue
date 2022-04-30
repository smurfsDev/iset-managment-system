<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-link to="/etudiant/demandeCreationClub"
        >Demande création club</router-link
      >
      <router-link to="/blogClub">Blog club</router-link>
      <router-link to="/admin/demandeCreationClub"
        >Demande création club Admin</router-link
      >
        <router-link to="/demandeMateriel"> demandeMateriel</router-link>

      <router-link to="/members"> members</router-link>
      <router-link to="/dashboard"> dashboardClub</router-link>
      
      <router-link to="/demandeMateriel"> DemandeMateriel</router-link>
      <router-link to="/demandeSalle"> DemandeSalle</router-link>
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
