<template>
  <b-card
    class="m-5"
  >
    <md-tabs style="height: auto;!important">
      <md-tab
        id="tab-home"
        style="height: auto;!important"
        md-label="emploi"
      >
      <div class="my-5 py-5 text-center" v-if="emploi.length == 0">
            <h3>Il y'a aucun Emploi</h3>
      </div>

        <div class="bv-example-row text-center" v-else>
          <b-row class="mb-2">
            <b-row>
              <b-col> Titre :{{ emploi.title }} </b-col>
            </b-row>
            <b-row>
              <b-col> Classe : {{ emploi.classes.abreviation }} </b-col>
            </b-row>
            <b-row v-if="emploi.file != null">
              <b-col>
                <img :src="emploi.file" alt="" width="70%" />
              </b-col>
            </b-row>
          </b-row>
        </div>
      </md-tab>
    </md-tabs>
  </b-card>
</template>

<script>
export default {
  data() {
    return {
      emploi: {
        id: 0,
        title: "",
        file: "",
        classes: {
          id: 0,
          abreviation: "",
        },
      },
    };
  },
  created() {
    this.getEmploi();
  },
  methods: {
    getEmploi() {
      this.$http.get("http://localhost:8000/api/EDT").then((response) => {
        this.emploi = response.data;
      }).catch(() => {
        this.emploi = [];
      });
    },
  },
};
</script>

<style>
</style>