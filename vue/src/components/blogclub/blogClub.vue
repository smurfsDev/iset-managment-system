<template>
  <div class="container">
    <navbar />
    <main class="container">
      <acceuil :id="id"/>

      <div class="row g-5">
        <div class="col-md-8">
          <h3 class="pb-4 mb-4 fst-italic border-bottom"></h3>
          <QuiSommesNous :id="id"/>
          <activites :id="id"/>
          <bureau :id="id"/>
          <projets :id="id"/>
          <statistiques :id="id"/>
        </div>
        <br />
        <br />
        <div class="col-md-4">
          <br />
          <br />
          <div class="position-sticky" style="top: 2rem">
            <historique :id="id"/>
            <actualites :id="id"/>
          </div>
        </div>
      </div>
    </main>
    <footerBlog />
  </div>
</template>

<script>
import navbar from "./blocs/navbar.vue";
import acceuil from "./blocs/acceuil.vue";
import QuiSommesNous from "./blocs/QuiSommesNous.vue";
import historique from "./blocs/historique.vue";
import activites from "./blocs/activites.vue";
import projets from "./blocs/projets.vue";
import bureau from "./blocs/bureau.vue";
import statistiques from "./blocs/statistiques.vue";
import actualites from "./blocs/actualites.vue";
import footerBlog from "./blocs/footerBlog.vue";

export default {
  data() {
    return {
      id: 0,
    };
  },
  created() {
    // check if route have param id
    if (this.$route.params.id) {
      this.id = this.$route.params.id;
    }
  },

  name: "blogClub",
  components: {
    navbar,
    acceuil,
    QuiSommesNous,
    historique,
    activites,
    projets,
    bureau,
    statistiques,
    actualites,
    footerBlog,
  },
};
</script>
<style>
/*@import "../../assets/css/blog.css";

@import "../../assets/css/features.css";

@import "../../assets/css/dropdowns.css";*/
</style>
