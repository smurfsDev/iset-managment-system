<template>
  <div class="container w-100 py-4">
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
              <form class="w-100" @submit.prevent="send($route.params.id)">
                <!-- one input for message bootstrap -->
                <div class="form-group">
                  <label for="event">Nom Event</label>
                  <input type="text" class="form-control" style="width:90%!important" id="event" disabled :value="$route.params.nomEvent">
                </div>
                <div class="form-group">
                  <label for="date">Date Event</label>
                  <input type="text" class="form-control" style="width:90%!important" id="date" disabled :value="$route.params.dateEvent">
                </div>
                <div class="form-group">
                  <label for="date">Nom club</label>
                  <input type="text" class="form-control" style="width:90%!important" id="club" disabled :value="$route.params.nomClub">
                </div>
                

                
                <button type="submit" class="btn btn-primary">Valider la demande </button>
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
    send(id) {
      

      console.log(this.message)
      this.$http.post("http://localhost:8000/api/demandeAdhesionEvent/create/"+id)
      .then((result) => {
       
        if (result.status == "201") {
          console.log(result.status)
          this.$router.push({ name:"demandeAdhesionEvent", params:{
            nomEvent: this.$route.params.nomEvent,
            dateEvent: this.$route.params.dateEvent,
            nomClub: this.$route.params.nomClub,

          }});
        }

      })
      ;
      this.message = "";
    },
  },
}
</script>

<style>

</style>