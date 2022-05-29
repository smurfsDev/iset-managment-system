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
        <div class="bv-example-row text-center">
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
      emploi: {},
    };
  },
  created() {
    this.getEmploi();
  },
  methods: {
    getEmploi() {
      this.$http.get("http://localhost:8000/api/EDT").then((response) => {
        this.emploi = response.data;
      });
    },
  },
};
</script>

<style>
</style>