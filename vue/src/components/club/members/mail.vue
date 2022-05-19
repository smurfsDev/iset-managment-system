<template>
  <div
    class="modal fade"
    id="MailModal"
    tabindex="-1"
    aria-labelledby="MailModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="MailModal">Envoi Email</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('MailModal')"
          ></button>
        </div>

        <form class="mb-3" @submit.prevent="addDemande">
          <div class="modal-body">
            <div class="form-group mb-2">
                {{use}}
                <label>Objet :</label>
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Objet"  required="required" />
              </div>
                <label>Message :</label>
                <textarea  class="form-control" placeholder="Message"  required="required" />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('MailModal')"
            >
              Fermer
            </button>
            <button type="submit" class="btn btn-primary">Sauvegarder</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    props: {
    use: Array,
    },
    data() {
    return {
       
    }
    },
  
  mounted() {},
  created() {
    },
  methods: {
      
  },
};
</script>

<style></style>
