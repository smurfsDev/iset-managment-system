<template>
  <div class="container w-100 py-4">
    {{ $route.params }}

    <!-- form containing one field for messages -->
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header">
            <h4 class="card-title">
              <i class="fas fa-envelope"></i> Demande
            </h4>
          </div>
          <div class="card-body">
            <b-container>
              <form class="w-100" @submit.prevent="send">
                <!-- one input for message bootstrap -->
                <div class="form-group">
                  <label for="message">Message</label>
                  <textarea class="form-control" v-model="message" style="width:80%!important" id="message" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </b-container>
          </div>
        </div>
      </div>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: "",
    };
  },
  methods: {
    send() {
      this.$http.post("http://localhost:8000/api/dac", {
        user_id : this.$store.getters.StateUser.id,
        club_id : this.$route.params.id,
        message: this.message,
      });
      this.message = "";
    },
  },
}
</script>

<style>

</style>